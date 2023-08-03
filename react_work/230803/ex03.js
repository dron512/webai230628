// const doA = ()=>{
//     return 'function doA';
// }
if (true) console.log('test');

const age = 20;
const name = '김길동';
const doA = () => 'function doA';

const person = {
  age,
  name,
  doA,
};

console.log(person.age);
console.log(person.name);

console.log(person.doA());
