class Person {
  getAge() {
    return 20;
  }
}
const p = new Person();
// p.getName = ()=>{ return '이름'; }
p.getName = function () {
  return '홍길동';
};
p.a = 10;
console.log(p.a);
console.log(p.getAge());
console.log(p.getName());
