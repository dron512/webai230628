const title = document.getElementById('title');
alert(title);

const arr = document.getElementsByClassName('container');
console.log(arr);
const divs = document.querySelectorAll('div');
console.log(divs);

for (const iterator of arr) {
  console.log(iterator);
  iterator.textContent = '내용바꾸기';
}

for (const iterator of divs) {
  console.log(iterator);
}
