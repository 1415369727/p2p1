<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎登陆商城系统</title>
<script src="${pageContext.request.contextPath }/js/mootools.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/styledate.css" />
<style type="text/css">
body{
	background:#fff url("${pageContext.request.contextPath }/images/wel2.jpg") repeat left top;
  	position: absolute;
	margin: 0;
	padding: 0;
	width: 100%;
	height: 100%;
	overflow: hidden;
	touch-action: none;
	content-zooming: none;
	/* overflow: hidden; */
	
}
 canvas {
	position: absolute;
	width: 100%;
	height: 100%;
	user-select: none;
} 
</style>

<script type="text/javascript">
var h_current = -1;
var m1_current = -1;
var m2_current = -1;
var s1_current = -1;
var s2_current= -1;


function flip (upperId, lowerId, changeNumber, pathUpper, pathLower){
	var upperBackId = upperId+"Back";
	$(upperId).src = $(upperBackId).src;
	$(upperId).setStyle("height", "64px");
	$(upperId).setStyle("visibility", "visible");
	$(upperBackId).src = pathUpper+parseInt(changeNumber)+".png";
	
	$(lowerId).src = pathLower+parseInt(changeNumber)+".png";
	$(lowerId).setStyle("height", "0px");
	$(lowerId).setStyle("visibility", "visible");
	
	var flipUpper = new Fx.Tween(upperId, {duration: 200, transition: Fx.Transitions.Sine.easeInOut});
	flipUpper.addEvents({
		'complete': function(){
			var flipLower = new Fx.Tween(lowerId, {duration: 200, transition: Fx.Transitions.Sine.easeInOut});
				flipLower.addEvents({
					'complete': function(){	
						lowerBackId = lowerId+"Back";
						$(lowerBackId).src = $(lowerId).src;
						$(lowerId).setStyle("visibility", "hidden");
						$(upperId).setStyle("visibility", "hidden");
					}				});					
				flipLower.start('height', 64);
				
		}
						});
	flipUpper.start('height', 0);
	
	
}//flip
			

function retroClock(){
	
	// get new time
	 now = new Date();
	 h = now.getHours();
	 m1 = now.getMinutes() / 10;
	 m2 = now.getMinutes() % 10;
	 s1 = now.getSeconds() / 10;
	 s2 = now.getSeconds() % 10;
	 if(h < 12)
	 	ap = "AM";
	 else{ 
	 	if( h == 12 )
			ap = "PM";
		else{
			ap = "PM";
			h -= 12; }
	 }
	 
	 //change pads
	 
	 if( h != h_current){
		flip('hoursUp', 'hoursDown', h, '${pageContext.request.contextPath }/images/Single/Up/'+ap+'/', '${pageContext.request.contextPath }/images/Single/Down/'+ap+'/');
		h_current = h;
	}
	
	if( m2 != m2_current){
		flip('minutesUpRight', 'minutesDownRight', m2, '${pageContext.request.contextPath }/images/Double/Up/Right/', '${pageContext.request.contextPath }/images/Double/Down/Right/');
		m2_current = m2;
		
		flip('minutesUpLeft', 'minutesDownLeft', m1, '${pageContext.request.contextPath }/images/Double/Up/Left/', '${pageContext.request.contextPath }/images/Double/Down/Left/');
		m1_current = m1;
	}
	
	 if (s2 != s2_current){
		flip('secondsUpRight', 'secondsDownRight', s2, '${pageContext.request.contextPath }/images/Double/Up/Right/', '${pageContext.request.contextPath }/images/Double/Down/Right/');
		s2_current = s2;
		
		flip('secondsUpLeft', 'secondsDownLeft', s1, '${pageContext.request.contextPath }/images/Double/Up/Left/', '${pageContext.request.contextPath }/images/Double/Down/Left/');
		s1_current = s1;
	}
	
	
	
		
	
}

setInterval('retroClock()', 1000);

</script>
</head>
<body>

<%-- <div id="wrapper">

	<div id="back">
         <div id="upperHalfBack">
         		<img src="${pageContext.request.contextPath }/images/spacer.png" /><img id="hoursUpBack" src="${pageContext.request.contextPath }/images/Single/Up/AM/0.png"/>
                <img id="minutesUpLeftBack" src="${pageContext.request.contextPath }/images/Double/Up/Left/0.png" class="asd" /><img id="minutesUpRightBack" src="${pageContext.request.contextPath }/images/Double/Up/Right/0.png"/>
                <img id="secondsUpLeftBack" src="${pageContext.request.contextPath }/images/Double/Up/Left/0.png" /><img id="secondsUpRightBack" src="${pageContext.request.contextPath }/images/Double/Up/Right/0.png"/>
         </div>
         <div id="lowerHalfBack">
         		<img src="${pageContext.request.contextPath }/images/spacer.png" /><img id="hoursDownBack" src="${pageContext.request.contextPath }/images/Single/Down/AM/0.png" />
               <img id="minutesDownLeftBack" src="${pageContext.request.contextPath }/images/Double/Down/Left/0.png" /><img id="minutesDownRightBack" src="Double/Down/Right/0.png" />
               <img id="secondsDownLeftBack" src="${pageContext.request.contextPath }/images/Double/Down/Left/0.png" /><img id="secondsDownRightBack" src="Double/Down/Right/0.png" />
         </div>
	</div>
    
    
    <div id="front">
         <div id="upperHalf">
         		<img src="${pageContext.request.contextPath }/images/spacer.png" /><img id="hoursUp" src="${pageContext.request.contextPath }/images/Single/Up/AM/0.png"/>
                <img id="minutesUpLeft" src="${pageContext.request.contextPath }/images/Double/Up/Left/0.png" /><img id="minutesUpRight" src="${pageContext.request.contextPath }/images/Double/Up/Right/0.png"/>
                <img id="secondsUpLeft" src="${pageContext.request.contextPath }/images/Double/Up/Left/0.png" /><img id="secondsUpRight" src="${pageContext.request.contextPath }/images/Double/Up/Right/0.png"/>
         </div>
         <div id="lowerHalf">
         		<img src="${pageContext.request.contextPath }/images/spacer.png" /><img id="hoursDown" src="${pageContext.request.contextPath }/images/Single/Down/AM/0.png"/>
               <img id="minutesDownLeft" src="${pageContext.request.contextPath }/images/Double/Down/Left/0.png" /><img id="minutesDownRight" src="${pageContext.request.contextPath }/images/Double/Down/Right/0.png" />
               <img id="secondsDownLeft" src="${pageContext.request.contextPath }/images/Double/Down/Left/0.png" /><img id="secondsDownRight" src="${pageContext.request.contextPath }/images/Double/Down/Right/0.png" />
         </div>
	</div>   
</div>
 --%>



<script>
"use strict";
{
	const txt = '欢迎登陆后台管理系统';
	const viscosity = 0.005;
	const stiffness = 0.99;
	class Point {
		constructor (i, x, y) {
			this.c = null;
			this.x0 = x;
			this.y0 = y;
			this.x = x + Math.sin(i) * 100;
			this.y = y + Math.cos(i) * 100;
			this.vx = 0.0;
			this.vy = 0.0;
			this.a = 0.0;
			this.s = 0.0;
			this.p0 = this;
			this.p1 = this;
			this.t = 0;
		}
		texture (c, color) {
			this.c = document.createElement("canvas");
			const ctx = this.c.getContext("2d");
			this.c.width = this.c.height = 450;
			ctx.font = "500px Arial Black, Arial";
			ctx.fillStyle = color;
			ctx.textAlign = "center";
			ctx.fillText(c, 200, 385);
		}
		drawSegment() {
			ctx.beginPath();
			ctx.strokeStyle = "#666";
			ctx.moveTo(this.p0.x, this.p0.y);
			ctx.lineTo(this.x, this.y);
			ctx.lineTo(this.p1.x, this.p1.y);
			ctx.stroke();
			ctx.beginPath();
			ctx.arc(this.x, this.y, 3, 0, 2 * Math.PI);
			ctx.stroke();
		}
		draw () {
			const fx = (this.x0 - this.x) * viscosity;
			const fy = (this.y0 - this.y) * viscosity;
			this.vx *= stiffness;
			this.vy *= stiffness;
			this.vx += fx;
			this.vy += fy;
			this.x += this.vx;
			this.y += this.vy;
			if (this.c !== null) {
				ctx.beginPath();
				ctx.save();
				ctx.translate(this.x, this.y);
				const dy = this.p1.x - this.p0.x;
				const dx = this.p1.y - this.p0.y;
				this.a = Math.atan2(dx, dy);
				this.s = Math.sqrt(dy * dy + dx * dx) / 2;
				ctx.rotate(this.a);
				ctx.drawImage(this.c, -this.s * 0.5, -this.s * 0.5, this.s, this.s);
				ctx.restore();
			}
		}
	}
	const points = [];
	const canvas = {
		init() {
			this.elem = document.createElement("canvas");
			document.body.appendChild(this.elem);
			this.resize();
			window.addEventListener("resize", () => this.resize(), false);
			return this.elem.getContext("2d");
		},
		resize() {
			this.width = this.elem.width = this.elem.offsetWidth;
			this.height = this.elem.height = this.elem.offsetHeight;
			let x = 0;
			const sx = this.width / (txt.length + 1);
			for (const p of points) {
				p.x0 = x;
				p.y0 = canvas.height / 2;
				x += sx;
			}
		}
	};
	const ctx = canvas.init();
	ctx.imageSmoothingEnabled = true;
	const pointer = {
		x: 0,
		y: 0,
		dx: 0,
		dy: 0,
		drag: null,
		over: null,
		down(e, touch) {
			this.move(e, touch);
			if (this.over) {
				this.dx = this.x - this.over.x;
				this.dy = this.y - this.over.y;
				this.drag = this.over;
				canvas.elem.style.cursor = "move";
			}
		},
		up(e, touch) {
			this.drag = null;
			canvas.elem.style.cursor = "default";
		},
		move(e, touch) {
			const pointer = touch ? e.targetTouches[0] : e;
			this.x = pointer.clientX;
			this.y = pointer.clientY;
			this.over = null;
			if (this.drag === null) {
				for (const p of points) {
					const dx = this.x - p.x;
					const dy = this.y - p.y;
					const d = Math.sqrt(dx * dx + dy * dy);
					if (d < p.s * 0.5) {
						canvas.elem.style.cursor = "pointer";
						this.over = p;
						break;
					} else {
						canvas.elem.style.cursor = "default";
					}
				}
			}
		},
		init() {
			window.addEventListener("mousedown", e => this.down(e, false), false);
			window.addEventListener("touchstart", e => this.down(e, true), false);
			window.addEventListener("mousemove", e => this.move(e, false), false);
			canvas.elem.addEventListener("touchmove", e => this.move(e, true), false);
			window.addEventListener("mouseup", e => this.up(e, false), false);
			window.addEventListener("touchend", e => this.up(e, true), false);
		}
	};
	{
		let x = 0;
		const sx = canvas.width / (txt.length + 1);
		for (let i = 0; i < txt.length + 2; i++) {
			points.push(new Point(i, x, canvas.height / 2));
			x += sx;
		}
		for (let i = 1; i < txt.length + 1; i++) {
			const p = points[i];
			p.p0 = points[i - 1];
			p.p1 = points[i + 1];
			p.s = sx;
			p.texture(txt.charAt(i - 1), i === 5 ? "#f80" : "#fff");
		}
	}
	const run = () => {
		requestAnimationFrame(run);
		ctx.clearRect(0, 0, canvas.width, canvas.height);
		for (const p of points) {
			if (pointer.drag === p) {
				p.x = pointer.x - pointer.dx;
				p.y = pointer.y - pointer.dy;
			}
			p.draw();
		}
		for (const p of points) {
			p.drawSegment();
		}
	}
	pointer.init();
	run();	
}
</script>
</body>
</html>