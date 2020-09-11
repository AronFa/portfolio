def distance(M, point_1, point_2):
    # storing the coordinates of the goal:
    x_2, y_2 = M.intersections[point_2]

    # storing the coordinates of the point:
    x_1, y_1 = M.intersections[point_1]

    # return the distance point_to_goal
    # since all coordinates are 0 < value < 1,
    # there is no need to worry about positives, negatives and such
    return ((x_2 - x_1) ** 2 + (y_2 - y_1) ** 2) ** .5


# calculate the cost of path travelled
def get_g_value(M, path):
    if type(path) != list:
        raise TypeError('PathIsNotList')
    elif len(path) < 2:
        raise ValueError(path)
        # 'PathNeedsTwoValuesMinimum'

    # path[0] = start
    curr_value = 0

    # walk down the route set by path
    for i in range(0, len(path) - 1):

        # check if the next value is possible
        if path[i + 1] not in M.intersections:
            raise ValueError('InvalidPath')
        # if it's valid the cost to the current cost value
        else:
            curr_value += distance(M, path[i], path[i + 1])

    return curr_value


# get an optimistic value for the rest of the way
def get_h_value(M, point, goal):
    # 99% of the distance of from point to goal should be a good h value
    return distance(M, point, goal)


# calculate the cost of the current path
def get_f_value(M, path, goal):
    return get_g_value(M, path) + get_h_value(M, path[-1], goal)


def shortest_path(M, start, goal):
    '''
    to implement A*

    I will store the current frontier and the intersections that have been explored.

    For every cycle i will explore the least expensive route stored in the the frontier one further
        moving it to explored and adding any paths it leads to to the frontier.

        I will have to make sure that:
        a) i will not explore already explored intersections again
        b) if i find a node of the frontier i will keep the cheaper path leading to that point.

    I will repeat these cycles until a value is popped from the goal. Than i will surely have the shortest path.

    '''

    # input handling
    if type(M) != Map:
        raise TypeError('MapIsNotClassMap')
    if type(start) != type(goal) != int:
        raise TypeError('StartAndGoalNeedToBeInt')
    # the get_g_value function does not work with len(path) < 2
    if start == goal:
        return [start]

    # this time i would prefer to implement it without recursion, as i have less practise with that
    # so stacks it is.

    explored = set()
    frontier = set()
    stack = []
    answer = None

    # initialize for the search
    popped_from_goal = False
    explored.add(start)
    for second_node in M.roads[start]:
        frontier.add(second_node)
        stack.append([start, second_node])

    while not popped_from_goal and stack:

        # find the cheapest route in the stack
        index_of_cheapest = None
        price_of_cheapest_so_far = None
        for possible_index, appended_path in enumerate(stack):

            # it has been popped
            if appended_path == None:
                continue

            # first hit
            if price_of_cheapest_so_far == None:
                price_of_cheapest_so_far = get_f_value(M, appended_path, goal)
                index_of_cheapest = possible_index

            # cheaper route fund than curr_cheapest
            elif get_f_value(M, appended_path, goal) < price_of_cheapest_so_far:
                price_of_cheapest_so_far = get_f_value(M, appended_path, goal)
                index_of_cheapest = possible_index

        # pop it,
        # and if it was popped from the goal, update the tracker accordingly
        path_to_walk = stack.pop(index_of_cheapest)
        if path_to_walk[-1] == goal:
            popped_from_goal = True
            answer = path_to_walk

        # move the popped intersections to the explored
        if path_to_walk[-1] in frontier:
            frontier.remove(path_to_walk[-1])
            explored.add(path_to_walk[-1])

        # update the frontier
        for item in M.roads[path_to_walk[-1]]:

            if item not in explored:
                # add any new intersections to the frontier
                frontier.add(item)
                # add any paths leading to the new frontier (back) to the stack
                new_path = path_to_walk.copy()
                new_path.append(item)
                stack.append(new_path)

    # return the result, or
    # raise error if it's not connected
    if frontier:
        return answer
    else:
        raise ValueError('PointsNotConnected')


class Map(object):

    def __init__(self):
        self.roads = None
        self.intersections = None


if __name__ == '__main__':
    map_10 = Map()
    map_10.intersections = {
        0: [0.7798606835438107, 0.6922727646627362],
        1: [0.7647837074641568, 0.3252670836724646],
        2: [0.7155217893995438, 0.20026498027300055],
        3: [0.7076566826610747, 0.3278339270610988],
        4: [0.8325506249953353, 0.02310946309985762],
        5: [0.49016747075266875, 0.5464878695400415],
        6: [0.8820353070895344, 0.6791919587749445],
        7: [0.46247219371675075, 0.6258061621642713],
        8: [0.11622158839385677, 0.11236327488812581],
        9: [0.1285377678230034, 0.3285840695698353]}
    map_10.roads = [
        [7, 6, 5],
        [4, 3, 2],
        [4, 3, 1],
        [5, 4, 1, 2],
        [1, 2, 3],
        [7, 0, 3],
        [0],
        [0, 5],
        [9],
        [8]
    ]

    map_40 = Map()
    map_40.intersections = {0: [0.7801603911549438, 0.49474860768712914],
                            1: [0.5249831588690298, 0.14953665513987202],
                            2: [0.8085335344099086, 0.7696330846542071],
                            3: [0.2599134798656856, 0.14485659826020547],
                            4: [0.7353838928272886, 0.8089961609345658],
                            5: [0.09088671576431506, 0.7222846879290787],
                            6: [0.313999018186756, 0.01876171413125327],
                            7: [0.6824813442515916, 0.8016111783687677],
                            8: [0.20128789391122526, 0.43196344222361227],
                            9: [0.8551947714242674, 0.9011339078096633],
                            10: [0.7581736589784409, 0.24026772497187532],
                            11: [0.25311953895059136, 0.10321622277398101],
                            12: [0.4813859169876731, 0.5006237737207431],
                            13: [0.9112422509614865, 0.1839028760606296],
                            14: [0.04580558670435442, 0.5886703168399895],
                            15: [0.4582523173083307, 0.1735506267461867],
                            16: [0.12939557977525573, 0.690016328140396],
                            17: [0.607698913404794, 0.362322730884702],
                            18: [0.719569201584275, 0.13985272363426526],
                            19: [0.8860336256842246, 0.891868301175821],
                            20: [0.4238357358399233, 0.026771817842421997],
                            21: [0.8252497121120052, 0.9532681441921305],
                            22: [0.47415009287034726, 0.7353428557575755],
                            23: [0.26253385360950576, 0.9768234503830939],
                            24: [0.9363713903322148, 0.13022993020357043],
                            25: [0.6243437191127235, 0.21665962402659544],
                            26: [0.5572917679006295, 0.2083567880838434],
                            27: [0.7482655725962591, 0.12631654071213483],
                            28: [0.6435799740880603, 0.5488515965193208],
                            29: [0.34509802713919313, 0.8800306496459869],
                            30: [0.021423673670808885, 0.4666482714834408],
                            31: [0.640952694324525, 0.3232711412508066],
                            32: [0.17440205342790494, 0.9528527425842739],
                            33: [0.1332965908314021, 0.3996510641743197],
                            34: [0.583993110207876, 0.42704536740474663],
                            35: [0.3073865727705063, 0.09186645974288632],
                            36: [0.740625863119245, 0.68128520136847],
                            37: [0.3345284735051981, 0.6569436279895382],
                            38: [0.17972981733780147, 0.999395685828547],
                            39: [0.6315322816286787, 0.7311657634689946]}
    map_40.roads = [
        [36, 34, 31, 28, 17],
        [35, 31, 27, 26, 25, 20, 18, 17, 15, 6],
        [39, 36, 21, 19, 9, 7, 4],
        [35, 20, 15, 11, 6],
        [39, 36, 21, 19, 9, 7, 2],
        [32, 16, 14],
        [35, 20, 15, 11, 1, 3],
        [39, 36, 22, 21, 19, 9, 2, 4],
        [33, 30, 14],
        [36, 21, 19, 2, 4, 7],
        [31, 27, 26, 25, 24, 18, 17, 13],
        [35, 20, 15, 3, 6],
        [37, 34, 31, 28, 22, 17],
        [27, 24, 18, 10],
        [33, 30, 16, 5, 8],
        [35, 31, 26, 25, 20, 17, 1, 3, 6, 11],
        [37, 30, 5, 14],
        [34, 31, 28, 26, 25, 18, 0, 1, 10, 12, 15],
        [31, 27, 26, 25, 24, 1, 10, 13, 17],
        [21, 2, 4, 7, 9],
        [35, 26, 1, 3, 6, 11, 15],
        [2, 4, 7, 9, 19],
        [39, 37, 29, 7, 12],
        [38, 32, 29],
        [27, 10, 13, 18],
        [34, 31, 27, 26, 1, 10, 15, 17, 18],
        [34, 31, 27, 1, 10, 15, 17, 18, 20, 25],
        [31, 1, 10, 13, 18, 24, 25, 26],
        [39, 36, 34, 31, 0, 12, 17],
        [38, 37, 32, 22, 23],
        [33, 8, 14, 16],
        [34, 0, 1, 10, 12, 15, 17, 18, 25, 26, 27, 28],
        [38, 5, 23, 29],
        [8, 14, 30],
        [0, 12, 17, 25, 26, 28, 31],
        [1, 3, 6, 11, 15, 20],
        [39, 0, 2, 4, 7, 9, 28],
        [12, 16, 22, 29],
        [23, 29, 32],
        [2, 4, 7, 22, 28, 36]]

    result = shortest_path(M=map_10, start=6, goal=5)
    if result == [6, 0, 5]:
        print("pass")
    else:
        print(result)
        print('is not')
        print([6, 0, 5])

    result = shortest_path(M=map_40, start=5, goal=5)
    if result == [5]:
        print("pass")
    else:
        print(result)
        print('is not')
        print([5])

    result = shortest_path(M=map_40, start=5, goal=34)
    if result == [5, 16, 37, 12, 34]:
        print("pass")
    else:
        print(result)
        print('is not')
        print([5, 16, 37, 12, 34])

    result = shortest_path(M=map_40, start=8, goal=24)
    if result == [8, 14, 16, 37, 12, 17, 10, 24]:
        print("pass")
    else:
        print(result)
        print('is not')
        print([8, 14, 16, 37, 12, 17, 10, 24])

    # this will be the same map as map_40, except
    # i manually removed all roads to node 24
    map_disconnected = Map()
    map_disconnected.intersections = {0: [0.7801603911549438, 0.49474860768712914],
                                      1: [0.5249831588690298, 0.14953665513987202],
                                      2: [0.8085335344099086, 0.7696330846542071],
                                      3: [0.2599134798656856, 0.14485659826020547],
                                      4: [0.7353838928272886, 0.8089961609345658],
                                      5: [0.09088671576431506, 0.7222846879290787],
                                      6: [0.313999018186756, 0.01876171413125327],
                                      7: [0.6824813442515916, 0.8016111783687677],
                                      8: [0.20128789391122526, 0.43196344222361227],
                                      9: [0.8551947714242674, 0.9011339078096633],
                                      10: [0.7581736589784409, 0.24026772497187532],
                                      11: [0.25311953895059136, 0.10321622277398101],
                                      12: [0.4813859169876731, 0.5006237737207431],
                                      13: [0.9112422509614865, 0.1839028760606296],
                                      14: [0.04580558670435442, 0.5886703168399895],
                                      15: [0.4582523173083307, 0.1735506267461867],
                                      16: [0.12939557977525573, 0.690016328140396],
                                      17: [0.607698913404794, 0.362322730884702],
                                      18: [0.719569201584275, 0.13985272363426526],
                                      19: [0.8860336256842246, 0.891868301175821],
                                      20: [0.4238357358399233, 0.026771817842421997],
                                      21: [0.8252497121120052, 0.9532681441921305],
                                      22: [0.47415009287034726, 0.7353428557575755],
                                      23: [0.26253385360950576, 0.9768234503830939],
                                      24: [0.9363713903322148, 0.13022993020357043],
                                      25: [0.6243437191127235, 0.21665962402659544],
                                      26: [0.5572917679006295, 0.2083567880838434],
                                      27: [0.7482655725962591, 0.12631654071213483],
                                      28: [0.6435799740880603, 0.5488515965193208],
                                      29: [0.34509802713919313, 0.8800306496459869],
                                      30: [0.021423673670808885, 0.4666482714834408],
                                      31: [0.640952694324525, 0.3232711412508066],
                                      32: [0.17440205342790494, 0.9528527425842739],
                                      33: [0.1332965908314021, 0.3996510641743197],
                                      34: [0.583993110207876, 0.42704536740474663],
                                      35: [0.3073865727705063, 0.09186645974288632],
                                      36: [0.740625863119245, 0.68128520136847],
                                      37: [0.3345284735051981, 0.6569436279895382],
                                      38: [0.17972981733780147, 0.999395685828547],
                                      39: [0.6315322816286787, 0.7311657634689946]}
    map_disconnected.roads = [
        [36, 34, 31, 28, 17],
        [35, 31, 27, 26, 25, 20, 18, 17, 15, 6],
        [39, 36, 21, 19, 9, 7, 4],
        [35, 20, 15, 11, 6],
        [39, 36, 21, 19, 9, 7, 2],
        [32, 16, 14],
        [35, 20, 15, 11, 1, 3],
        [39, 36, 22, 21, 19, 9, 2, 4],
        [33, 30, 14],
        [36, 21, 19, 2, 4, 7],
        [31, 27, 26, 25, 18, 17, 13],
        [35, 20, 15, 3, 6],
        [37, 34, 31, 28, 22, 17],
        [27, 18, 10],
        [33, 30, 16, 5, 8],
        [35, 31, 26, 25, 20, 17, 1, 3, 6, 11],
        [37, 30, 5, 14],
        [34, 31, 28, 26, 25, 18, 0, 1, 10, 12, 15],
        [31, 27, 26, 25, 1, 10, 13, 17],
        [21, 2, 4, 7, 9],
        [35, 26, 1, 3, 6, 11, 15],
        [2, 4, 7, 9, 19],
        [39, 37, 29, 7, 12],
        [38, 32, 29],
        [],
        [34, 31, 27, 26, 1, 10, 15, 17, 18],
        [34, 31, 27, 1, 10, 15, 17, 18, 20, 25],
        [31, 1, 10, 13, 18, 25, 26],
        [39, 36, 34, 31, 0, 12, 17],
        [38, 37, 32, 22, 23],
        [33, 8, 14, 16],
        [34, 0, 1, 10, 12, 15, 17, 18, 25, 26, 27, 28],
        [38, 5, 23, 29],
        [8, 14, 30],
        [0, 12, 17, 25, 26, 28, 31],
        [1, 3, 6, 11, 15, 20],
        [39, 0, 2, 4, 7, 9, 28],
        [12, 16, 22, 29],
        [23, 29, 32],
        [2, 4, 7, 22, 28, 36]]

    try:
        shortest_path(map_disconnected, 8, 24)
        print('this should have raised an Error')
    except ValueError:
        print('pass')
