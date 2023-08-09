import './App.css';
import Ex02 from './components/Ex02';

function App() {
  return (
    <div className="App">
      <Ex02 name="홍길동" age={20}></Ex02>
      <Ex02 name="김길동" age={30}></Ex02>
      <Ex02 name="이길동" age={40}></Ex02>
      {/* <Ex01 /> */}
      {/* <Ex01 />
      <Ex01 />
      <Ex01 /> */}
      {/* <Ex01_1 /> */}
    </div>
  );
}

export default App;
