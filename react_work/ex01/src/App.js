import { useEffect } from 'react';
import './App.css';
import MyA from './components/MyA';

const doA = () => {
  const obj1 = { a: 10, b: 20 };
  const obj2 = { ...obj1 };
  const { a, b } = obj1;
  console.log(`obj2 ${obj2}`);
  console.log(`a = ${a}`);
  console.log(`b = ${b}`);
};

function App() {
  // 컴포넌트 호출시에 한번 호출되는 함수
  useEffect(() => {
    doA();
  });
  return (
    <div className="App">
      <h1>안녕하세요</h1>
      <MyA a="10" b="홍길동"></MyA>
      <MyA a="20" b="김길동"></MyA>
      <MyA a={30}></MyA>
      {/* {MyA(40)} */}
    </div>
  );
}

export default App;
