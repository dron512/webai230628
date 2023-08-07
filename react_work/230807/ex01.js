const arr1 = [10, 20];
const arr2 = [30, 40];

const arr3 = [...arr1, ...arr2];

const obj1 = { a: 10, b: 20 };
const obj2 = { c: 30, d: 40 };

const obj3 = { ...obj1, obj2 };

console.log(arr3);
console.log(obj3);

const obj4 = { ...obj3, a: 50 };

console.log(obj4);
