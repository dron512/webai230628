const arr = [10, 20, 30];

const [num1, num2, num3] = arr;

console.log(num1);
console.log(num2);
console.log(num3);

const doA = (num1 = 30) => {
  console.log(`num1 ${num1}`);
};

doA();
doA(20);

const data = { age: 20 };

const { name: myName = '홍길동', age } = data;
console.log(`name ${myName}`);
console.log(`age ${age}`);
