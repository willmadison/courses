package fundamentals_test

import (
	"fmt"
	"testing"

	"github.com/stretchr/testify/assert"
	fundamentals "github.com/willmadison/courses/git-fundamentals"
)

func TestSpeak(t *testing.T) {
	cases := []struct {
		given, expected string
	}{
		{
			"Class",
			"Hello, Class",
		},
		{
			"Ms. Hannigan",
			"Hello, Ms. Hannigan",
		},
	}

	for _, tc := range cases {
		t.Run(fmt.Sprintf("Speak(%v)", tc.given), func(t *testing.T) {
			actual := fundamentals.Speak(tc.given)
			assert.Equal(t, tc.expected, actual)
		})
	}
}
