const array = [10, 20, 30, 40];

for (let index = 0; index < array.length; index++) {
  const element = array[index];
  console.log(`element = ${element}`);
}
console.log('\n');

array.map(element => {
  console.log(`element = ${element}`);
});
console.log('\n');

array.forEach(element => {
  console.log(`element = ${element}`);
});
console.log('\n');

for (const key in array) {
  if (Object.hasOwnProperty.call(array, key)) {
    const element = array[key];
    console.log(`element = ${element}`);
  }
}
console.log('\n');

for (const element of array) {
  console.log(`element = ${element}`);
}
