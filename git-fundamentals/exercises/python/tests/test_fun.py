import unittest
from fun import greeter

class GreetingTests(unittest.TestCase):

    def test_it_should_properly_greet_a_user(self):
        cases = [
            ('Ms. Hannigan', 'Hello, Ms. Hannigan'),
            ('Class', 'Hello, Class')
        ]

        for c in cases:
            name = c[0]
            expected = c[1]
            actual = greeter.speak(name)
            self.assertEqual(expected, actual)


if __name__ == '__main__':
    unittest.main()