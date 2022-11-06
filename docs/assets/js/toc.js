const button = document.querySelector(".toc-button")
const content = document.querySelector(".post-content")
const header = document.querySelector(".post-title")
let height = header.offsetHeight + 140 // adjustment for margins
for (let i = 0; i < content.children.length; ++i) {
    if (content.children[i].tagName === "HR") {
        break;
    }
    height += content.children[i].offsetHeight
}
const addNumbering = (init, level) => {
    const numbering = init.toString().split(" ").join(".") + ". "
    level.children[0].innerHTML = numbering + level.children[0].innerHTML
    if (level.children.length > 1) {
        let nextLevels = level.children[1].querySelectorAll(":scope > li")
        for (let i = 0; i < nextLevels.length; ++i) {
            addNumbering(init + " " + (i + 1), nextLevels[i])
        }
    }
}
const firstLevels = document.querySelectorAll("#markdown-toc > li");
for (let i = 0; i < firstLevels.length; ++i) {
    addNumbering((i + 1), firstLevels[i])
}
window.onscroll = function () {
    if (document.body.scrollTop > height || document.documentElement.scrollTop > height) {
        button.style.opacity = "1";
    } else {
        button.style.opacity = "0";
    }
}
