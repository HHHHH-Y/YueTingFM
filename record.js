// 视频, 音频上传功能练习

// document 是在浏览器中运行时一直存在的一个变量, 表示的意思是代表文档树
// html Document Object Model Tree  DOM 树
// document 粗糙的可以理解为这棵树的根

// getElementById 从树上, 根据 id, 找到对应的节点(标签)
let preview = document.getElementById("preview");  // <video id = "preview" width="160" height="120" autoplay muted></video>
let recording = document.getElementById("recording");
let startButton = document.getElementById("startButton");
let stopButton = document.getElementById("stopButton");
let downloadButton = document.getElementById("downloadButton");

let mediaRecorder;

function startRecording() {
    console.log("点击了开始录制");
    // 开启了用户设备, 既要录制视频, 又要录制音频
    // 会有一个弹窗, 触发申请权限操作, 询问用户是否可以使用摄像头和麦克风
    let promise = navigator.mediaDevices.getUserMedia({
        video: true,   // 申请摄像头权限
        audio: true    // 申请麦克风权限
    });

    // 如果用户同意了, 就执行 then 中的方法, 如果失败(用户不同意 / 其他失败), 就执行 catch 中的方法 
    promise.then(function(stream) {
        // 用户同意使用麦克风和摄像头
        // stream 变量就代表录制的视频和音频
        preview.srcObject = stream; // 将用户摄像头捕获的视频显示在预览框中
        mediaRecorder = new MediaRecorder(stream); // 构造了一个媒体计数器
        // 当数据可用的时候
        mediaRecorder.onataavailable = function(event) {
            console.log(event);
        };
        // 开始录制
        mediaRecorder.start();
    }).catch(e => {
        console.log(e);
    });
}

function stopRecording() {
    console.log("点击了结束录制");
    
}


// 这种形态就是俗称的回调函数(callback)
// startButton.addEventListener("click", startRecording);  <-- 和下面的写法, 目前可以认为是一样的效果
startButton.onclick = startRecording;// 进行事件绑定, 发生了 startButton 的点击(click)事件后, 请执行 startRecording 方法



stopButton.onclick = stopRecording;// 在 stopButton 发生了(on)点击(click)事件后, 执行 stopRecoding