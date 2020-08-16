// 视频, 音频上传功能练习
console.log("OK");

// document 是在浏览器中运行时一直存在的一个变量, 表示的意思是代表文档树
// html Document Object Model Tree  DOM 树
// document 粗糙的可以理解为这棵树的根

// getElementById 从树上, 根据 id, 找到对应的节点(标签)
let preview = document.getElementById("preview");  // <video id = "preview" width="160" height="120" autoplay muted></video>
let recording = document.getElementById("recording");
let startButton = document.getElementById("startButton");
let stopButton = document.getElementById("stopButton");
let downloadButton = document.getElementById("downloadButton");


function wait(delayInMS) {
    // setTimeout(执行什么方法, 多少毫秒之后)
    // 类似于 java 中的定时器(Timer)
    // 设定一个闹钟的效果
    return new Promise(resolve => setTimeout(resolve, delayInMS));
  }

function startRecording(stream, lengthInMS) { // lengthInMS: 表示的是以毫秒为单位的录制长度
    console.log("开始录制");

    let recorder = new MediaRecorder(stream); // 定义一个媒体录制对象
    let data = []; // 用于存放录下来的数组

    // on(当)数据(data)可用(available)时, 执行该方法
    recorder.ondataavailable = function(event) {
        console.log("数据可用");
        // event.data 录制下来的视频和音频数据, 存入 data 数组中
        data.push(event.data); // 线性表的尾插
    };

    // 开始录制
    recorder.start();

    // resolve 成功的时候应该执行的方法, 对应 then 传入的方法
    // reject 失败时应该执行的方法, 对应 catch 传入的方法
    let stopped = new Promise(function(resolve, reject) {
        recorder.onstop = resolve;
        recorder.onerror = function(event) {
            reject(event.name);
        }
    });

    // 持续 lengthInMS 时间后, 执行 then 中的方法
    let recorded = wait(lengthInMS).then(
        function() {
            // 5 秒之后
            // 判断 recorder 是否还在录制, 如果还在录制 == "recording", 则停止录制
            if(recorder.state == "recording") {
                console.log("停止录制");
                recorder.stop();
            }
        }
    );

    return Promise.all([
        stopped,
        recorded
    ])
    .then(() => data);
}

function startCapture() {
    console.log("开始采集");
    // 开启了用户设备, 既要录制视频, 又要录制音频
    // 会有一个弹窗, 触发申请权限操作, 询问用户是否可以使用摄像头和麦克风
    let promise = navigator.mediaDevices.getUserMedia({
        video: true,   // 申请摄像头权限
        audio: true    // 申请麦克风权限
    });

    // 如果用户同意了, 就执行 then 中的方法, 如果失败(用户不同意 / 其他失败), 就执行 catch 中的方法 
    promise.then(function(stream) {
        // 用户同意使用麦克风和摄像头
        console.log("用户同意授权");
        // stream 变量就代表录制的视频和音频

        // 为 preview 设置 src 属性, video 播放的视频来自摄像头采集的数据
        preview.srcObject = stream; // 将用户摄像头捕获的视频显示在预览框中

        // 处理兼容性的, 类似 if(!preview.captureStream) { preview.captureStream = preview.mozCaptureStream;}
        preview.captureStream = preview.captureStream || preview.mozCaptureStream;

        // 接着执行的是, 当 preview 开始(on)播放(playing)时, 执行 then 的方法
        return new Promise(resolve => preview.onplaying = resolve); // 最后这个 resolve 就是接下来的 function() 方法
    }).then(function() {
        // 当 preview 开始 playing 时, 会执行 startRecording(...)
        return startRecording(preview.captureStream(), 5000);
    }).then(function(data) {
        console.log("使用录制下来的数据");
        console.log(data);

        // 将我们上面 video 中录制的数据转换成一个 Blob 的数据, 也就是一种二进制流的数据
        let recordedBlob = new Blob(data, { type: "video/webm"});
        recording.src = URL.createObjectURL(recordedBlob); // 将这个数据设置到下面响应的 video 的 src 中
    })
    .catch(e => {
        console.log(e);
    });
}

function stopRecording() {
    console.log("点击了结束录制");
    
}


// 这种形态就是俗称的回调函数(callback)
// startButton.addEventListener("click", startRecording);  <-- 和下面的写法, 目前可以认为是一样的效果
startButton.onclick = startCapture;// 进行事件绑定, 发生了 startButton 的点击(click)事件后, 请执行 startRecording 方法


stopButton.onclick = stopRecording;// 在 stopButton 发生了(on)点击(click)事件后, 执行 stopRecoding