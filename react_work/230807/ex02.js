const arr = ['누시다', '사키오카', '교토'];

const arr2 = arr.map(name => name);

console.log(arr2);

const a = 10;
const b = '10';

console.log(a == b);
console.log(a === b);

const arr3 = arr2.filter(name => {
  return name !== '사키오카';
});

console.log(arr3);

arr3.map((name, index) => {
  console.log(`${index}번째는 ${name} 입니다`);
});
