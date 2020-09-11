def rotated_array_search(input_list, target_number):
    """
    Find the index by searching in a rotated sorted array

    Args:
       input_list(array)        input array
       target_number(int)     target
       It will search the input array for the target
    Returns:
       int: Index or -1
    """

    # The easiest way for me to look at the rotated sorted list is to see it
    # as two sorted lists with a midpoint at unknown index.
    # So the code will first find the midpoint,
    # than try to find the value at the correct side of the list.

    # check the input
    if type(input_list) != list:
        raise TypeError('InputListIsNotList')
    elif len(input_list) == 0:
        return -1
    if not (type(target_number) == int or type(target_number) == float):
        raise TypeError('TargetNumberIsNotNumber')

    # 1) Find the midpoint
    midpoint_left = None
    # check if it has been rotated (it's random so it can be the original form too)
    if input_list[0] > input_list[-1]:
        midpoint_min = 0
        midpoint_max = len(input_list) - 1
        midpoint_left = (midpoint_max - midpoint_min) // 2
        while input_list[midpoint_left] < input_list[midpoint_left + 1]:

            # while searching for the midpoint i might as well see if that check got lucky
            if input_list[midpoint_left] == target_number:
                # print('got lucky case #1')
                return midpoint_left
            elif input_list[midpoint_left + 1] == target_number:
                # print('got lucky case #2')
                return midpoint_left + 1

            midpoint_left = (midpoint_max + midpoint_min) // 2

            if input_list[midpoint_left] > input_list[0]:
                midpoint_min = midpoint_left + 1

            if input_list[midpoint_left] < input_list[-1]:
                midpoint_max = midpoint_left - 1

        # clean it up
        del midpoint_min, midpoint_max

    # 2) Find the target in the correct half of the list

    # setting up the search
    find_min = 0                        # needs to default to the while range as it might be just a regular sorted list
    find_max = len(input_list) - 1

    # narrow the rage if it is indeed rotated
    if midpoint_left:                   # midpoint_left is None in case it's unsorted

        # Initializing so the search will take place on the correct half, if there are halves
        if input_list[0] <= target_number <= input_list[midpoint_left]:             # search the left list
            find_min = 0
            find_max = midpoint_left
        elif input_list[midpoint_left + 1] <= target_number <= input_list[-1]:      # search the right list
            find_min = midpoint_left + 1
            find_max = len(input_list) - 1

    # finishing the initialization
    find_guess = (find_min + find_max) // 2

    # searching for the target
    while input_list[find_guess] != target_number:

        find_guess = (find_max + find_min) // 2

        if input_list[find_guess] > target_number:      # go left
            find_max = find_guess - 1

        if input_list[find_guess] < target_number:      # go right
            find_min = find_guess + 1

        if find_min == find_max:                        # the search has ended
            # find_guess needs to be updated,
            # because the search might have ended this cycle,
            # and that would bug it out (could also return here too)
            find_guess = find_min
            break

    if input_list[find_guess] == target_number:
        return find_guess
    else:
        return -1


def linear_search(input_list, number):
    for index, element in enumerate(input_list):
        if element == number:
            return index
    return -1


def test_function(test_case):
    input_list = test_case[0]
    number = test_case[1]
    if linear_search(input_list, number) == rotated_array_search(input_list, number):
        print("Pass")
    else:
        print("Fail")


if __name__ == '__main__':

    # Test with short, rotated lists
    print('\nTest with rotated lists')
    test_function([[6, 7, 8, 9, 10, 1, 2, 3, 4], 6])
    test_function([[6, 7, 8, 9, 10, 1, 2, 3, 4], 1])
    test_function([[6, 7, 8, 1, 2, 3, 4], 8])
    test_function([[6, 7, 8, 1, 2, 3, 4], 1])
    test_function([[6, 7, 8, 1, 2, 3, 4], 10])

    # Tests with un-rotated lists
    print("\nTest with lists that are un-rotated, 'regular' sorted")
    test_function([[0, 1, 3, 4, 5, 7, 9, 11], 7])
    test_function([[0, 1, 3, 4, 5, 7, 9, 11], 0])
    test_function([[1, 4, 6, 7, 9, 14, 78, 100], 14])

    # Test with other edge cases
    print('\nTest with negative numbers')
    test_function([[-10, -6, -4, -1, 0, 4, -17, -13, -11], 0])
    test_function([[-10, -6, -4, -1, 0, 4, -17, -13, -11], 99])

    print('\nTest with decimal values')
    test_function([[0.6, 0.7, 0.8, 0.9, 1.1, 0.1, 0.2, 0.3, 0.4], 0.6])
    test_function([[0.6, 0.7, 0.8, 0.9, 1.1, 0.1, 0.2, 0.3, 0.4], 0])

    print('\nTest empty list')
    test_function([[], 1])

