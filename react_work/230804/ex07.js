const doA = (num1, num2, num3) => {
  console.log(num1);
  console.log(num2);
  console.log(num3);
};

const arr = [10, 20, 30];

doA(arr);
doA(...arr);

const brr = [...arr];
console.log(brr);

arr[0] = 50;
console.log(`brr`);
console.log(...brr);
console.log(`arr`);
console.log(...arr);
