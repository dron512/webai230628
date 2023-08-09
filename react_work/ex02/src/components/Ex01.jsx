import React from 'react';

const MyColorCompoent = (props)=>{
    console.log(props);
    return (
        <h1 style={{color:props.color,fontSize:props.size}}>{props.text}</h1>
    );
}
const AA = ({name,age})=>{
    return(
        <>이름은 {name} 나이는 {age} 입니다</>
    )
}
const onMyBtn = ()=>{
    alert('test');
}
const pStyle = { color:"blue"}
const Ex01 = () => {
    return (
        <>
            <h1 style={{color:"red"}}>Ex01</h1>
            <p style={pStyle}>Ex01의 p 문단태그입니다</p>
            <button onClick={onMyBtn}>버튼</button>
            <MyColorCompoent color="red" size={50} text="안녕하세요"></MyColorCompoent>
            <MyColorCompoent color="blue" size={10} text="ㅎㅎㅎ"></MyColorCompoent>
            <AA name="홍길동" age={10}/>
            <br/>
            <AA name="김길동" age={20}/>
        </>
    );
}
export const Ex01_1 = () => {
    return ( 
    <>
        <h1>Ex01_1</h1>
    </> 
    );
}
 
export default Ex01;