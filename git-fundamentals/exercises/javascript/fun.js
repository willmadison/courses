const speak = (name) => {
    return fancySub(name, "Hello, [placeholder]");
}

const fancySub = (value, template) => {
    return template.replace("[placeholder]", value);
}

module.exports.speak = speak;