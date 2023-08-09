import React from 'react';

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