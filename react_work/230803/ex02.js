const a = [];
const b = {};
const c = 10;

a.push(10);
a.push(20);

console.log(a);

b.getAge = () => 10;
b.getName = () => '홍길동';

console.log(b);
console.log(b.getAge());
console.log(b.getName());

try {
  c = 40;
} catch (e) {
  console.log(e);
}
console.log(c);
