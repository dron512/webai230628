const num1 = 10;
console.log(typeof num1);
console.log(typeof num1);

const num2 = '10';
console.log(typeof num2);
console.log(typeof num2);

const doA = num => {
  const formatNum = typeof num === 'number' ? num.toLocaleString() : '숫자 입력하세요';
  return formatNum;
};
console.log(doA());
console.log(doA);
console.log(doA(1300));
