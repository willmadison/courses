var assert = require('assert');
var fun = require('../fun')

describe('Fun', function () {
    describe('#speak()', function () {
        it('should return Hello, ${name} when given a name', function () {
            assert.equal(fun.speak('Ms. Hannigan'), 'Hello, Ms. Hannigan');
        });
    });
});