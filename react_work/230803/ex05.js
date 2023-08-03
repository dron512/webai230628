function doA(a) {
  console.log(this);
  console.log(a);
}
const doB = (a, b) => {
  console.log(this);
  console.log(a);
  console.log(b);
  return a;
};

const a = 'asdfasdf';

// doA('test');
console.log(doB('test'));
