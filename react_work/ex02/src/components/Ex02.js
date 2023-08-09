import React, { useEffect, useState } from 'react';

const Ex02 = ({ name, age }) => {
  const [test, setTest] = useState('한국');
  useEffect(() => {
    console.log('무조건 호출됨');
    // fetch('http://localhost:8080')
    //   .then(() => {})
    //   .then(() => {
    //     setTest('미국');
    //   });
    setTimeout(() => {
      setTest('미국');
    }, 3000);
  });

  console.log('함수 호출되면서 랜더링');
  const [uage, setUage] = useState(age);
  const doAdd = () => {
    setUage(uage + 1);
    // age = age + 1;
    // console.log(age);
  };
  const doSub = () => {
    setUage(uage - 1);
    // age = age - 1;
    // console.log(age);
  };

  if (test === '한국') return <h1>'Loading...'</h1>;

  return (
    <>
      <h1>국적 {test}</h1>
      <h1>이름 {name}</h1>
      <p>나이 {uage}</p>
      <button onClick={doAdd}>나이증가</button>
      <button onClick={doSub}>나이감소</button>
    </>
  );
};

export default Ex02;
