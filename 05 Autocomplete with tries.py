"""
    Tires are structures with the intent of storing all words in accessible and space efficient manner.
    This is achieved by storing the them as a tree:
        Words are stored character by character, so a word itself is the route taken on the tree.
            ('mean' will be stored as 'm' -> 'e' -> 'a' -> 'n' )
        With each letter the tree is responsible for:
            - knowing what characters are possible following the current, and
            - whether the current path is representing a valid word or not

    This is implemented with TrieNode's, and Trie's:

        class 'TrieNode's

            These nodes will be states of the search:
                in looking up the word 'mean' the route will be 'm' -> 'e' -> 'a' -> 'n'
                and each of those 'stations' will be a TrieNode

            have class variables:
            .children:      dict()          all_possible_next_letters   ->  TrieNodes_representing_those_letters
            .is_word:       bool()          whether the the route taken represents a valid word

        class 'Trie's

            This is responsible for storing all the data in one easily accessible place

            have one class variable:
            .root:          TireNode()      this is implemented to keep track of the start of the words

            have methods:
            .insert         for adding new words
            .find:          for finding all valid words with given start (prefix)
    """


# Represents a single node in the Trie
class TrieNode:
    """
    class 'TrieNode's

    These nodes will be states of the search:
        in looking up the word 'mean' the route will be 'm' -> 'e' -> 'a' -> 'n'
        and each of those 'stations' will be a TrieNode

    have class variables:
    .children:      dict()          all_possible_next_letters   ->  TrieNodes_representing_those_letters
    .is_word:       bool()          whether the the route taken represents a valid word

    """
    # Initialize this node in the Trie
    def __init__(self):
        self.is_word = False
        self.children = dict()

    # insert a child TrieNode callable with 'char'
    def add_chr_to_children(self, char):
        self.children[char] = TrieNode()

    def word_entered(self):
        self.is_word = True


# The Trie itself containing the root node and insert/find functions
class Trie:
    """
    class 'Trie's

    This is responsible for storing all the data in one easily accessible place

    have one class variable:
    .root:          TireNode()      this is implemented to keep track of the start of the words

    have methods:
    .insert             use this to add new words
    .find(prefix):      use this to find all valid words with given start (prefix)
    """

    # Initialize this Trie (add a root node)
    def __init__(self):
        self.root = TrieNode()

    # Add a word to the Trie
    def insert(self, word):

        # initialize the insertion
        curr_node = self.root

        # checking if char has been added, if not adding it
        for char in word:
            if char not in curr_node.children:          # has not been added
                curr_node.add_chr_to_children(char)
                curr_node = curr_node.children[char]
            else:                                       # this is the first time it's entered
                curr_node = curr_node.children[char]

        # saving the route as a valid word
        curr_node.word_entered()

    # returning all the words starting with 'prefix'
    def find(self, prefix):

        # Find the Trie node that represents this prefix

        # initializing the search
        curr_node = self.root

        # going down the route set by 'prefix'
        for char in prefix:
            if char in curr_node.children:
                curr_node = curr_node.children[char]
            else:
                # raise ValueError('PrefixNotFound')
                return None

        # set up for a search
        suffixes = []
        char_string = ''

        # writing the search
        def rec_search_of_suffixes(head_node, possible_suffix, suffixes_so_far):
            for child in head_node.children:

                # that child is a word
                if head_node.children[child].is_word:
                    suffixes_so_far.append(possible_suffix + child)

                # that child has more children
                suffixes_so_far + rec_search_of_suffixes(head_node.children[child], possible_suffix + child,
                                                         suffixes_so_far)
            return suffixes_so_far

        # calling the search
        return rec_search_of_suffixes(curr_node, char_string, suffixes)


if __name__ == '__main__':

    fruit_list = ['apricot', 'banana', 'cherry', 'dragonfruit', 'elderberry', 'fig', 'grape', 'honey', 'ibuprofen',
                  'juniper', 'kiwi', 'lime', 'mango', 'no_idea', 'olive', 'plum', 'q_???', 'raspberry', 'strawberry',
                  'tangerine', 'vanilla', 'watermelon', 'it_seemed_fun_but_now_i_give_up']

    food_with_a_list = ['apple pie', 'apple tart', 'almonds', 'anchovies', 'apricot', 'almond milk', 'artichoke',
                        'arugula', 'asparagus', 'avocado', 'after eight', 'anise', 'aniseed', 'alphabet soup',
                        'apple juice', 'amaretto', 'ambrosia']

    # initializing the library for the autocomplete
    autocomplete_lib = Trie()

    # Add words
    for edible_list in [fruit_list, food_with_a_list]:
        for word in edible_list:
            autocomplete_lib.insert(word.lower())

    # test the prefixes
    test_prefixes = ['ab', 'app', 'am', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j']
    for prefix in test_prefixes:
        print(prefix, autocomplete_lib.find(prefix))
