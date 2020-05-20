// 获取HTML中的对象
    var parent = document.getElementById("parent");
    var img_ul = document.getElementById("img_ul");
    var litCir_ul = document.getElementById("litCir_ul");
    var buttons = document.getElementById("buttons");
    var cLis =litCir_ul.children;

    var len = img_ul.children.length;     //图片张数
    var width = parent.offsetWidth;       //每张图片的宽度
    var rate = 15;                        //一张图片的切换速度， 单位为px
    var times = 1;                        //切换速度的倍率
    var gap = 2000;                       //自动切换间隙， 单位为毫秒
    var timer = null;                     //初始化一个定时器
    var picN = 0;                         //当前显示的图片下标
    var cirN = 0;                         //当前显示图片的小圆点下标
    var temp;

//添加小圆点
    for (var i=0; i<len; i++){
        var a_li = document.createElement("li");
        a_li.className = 'quiet';
        litCir_ul.appendChild(a_li);
    }
    litCir_ul.children[0].className = "active";

//实现无缝滚动
    function Roll(distance){                                         //参数distance：滚动的目标点（必为图片宽度的倍数）
        clearInterval(img_ul.timer);                                     //每次运行该函数必须清除之前的定时器！
        var speed = img_ul.offsetLeft < distance ?  rate : (0-rate);     //判断图片移动的方向
        
        img_ul.timer = setInterval(function(){                           //设置定时器，每隔10毫秒，调用一次该匿名函数
            img_ul.style.left = img_ul.offsetLeft + speed + "px";        //每一次调用滚动到的地方 (速度为 speed px/10 ms)         
            var leave = distance - img_ul.offsetLeft;                    //距目标点剩余的px值      
            /*接近目标点时的处理，滚动接近目标时直接到达， 避免rate值设置不当时不能完整显示图片*/
            if (Math.abs(leave) <= Math.abs(speed)) {                    
                clearInterval(img_ul.timer);
                img_ul.style.left = distance + "px";
            }
        },10);
        }

        /*克隆第一个li到列表末*/
    img_ul.appendChild(img_ul.children[0].cloneNode(true));
//自动滚动
    function autoRun(){
        picN++;
        cirN++;
        if(picN > len){                  //滚动完克隆项后
            img_ul.style.left = 0;       //改变left至真正的第一项处
            picN = 1;                    //从第二张开始显示
        }
        Roll(-picN*width);
        
        if(cirN > len-1){                //判断是否到了最后一个圆点
            cirN = 0;                 
        }
        for(var i=0; i<len; i++){
            cLis[i].className = "quiet";
        }
        cLis[cirN].className = "active";
    }
//触及小圆点时切换至对应图片
    for(var i=0; i<len; i++){
        cLis[i].index = i;
        cLis[i].onmouseover = function(){
            for(var j=0; j<len; j++){
                cLis[j].className = "quiet";
            }
            this.className = "active";
            temp = cirN;
            picN = cirN = this.index;
            times = Math.abs(this.index - temp);  //距离上个小圆点的距离
            rate = rate*times;                    //根据距离改变切换速率
            Roll(-this.index * width);
            rate = 15;
        }
    }


//触及轮播图区域和离开该区域时
    parent.onmouseover = function(){
        clearInterval(timer);
        buttons.style.display = 'block';
    }
    parent.onmouseout = function(){
        timer = setInterval(autoRun, gap);
        buttons.style.display = 'none';
    }

//给两个按钮添加onclick事件
    /*上一张*/
    buttons.children[0].onclick = function(){ 
        picN--;
        cirN--;
        if(picN < 0){                               //滚动完第一项后
            img_ul.style.left = -len*width + "px";  //改变left至克隆的第一项处
            picN = cirN = len-1;
        }
        Roll(-picN*width);
        //bug处理
        if(cirN < 0){
            cirN = len-1;
        }
        for(var i=0; i<len; i++){
            cLis[i].className = "quiet";
        }
        cLis[cirN].className = "active";
    }
    /*下一张*/
    buttons.children[1].onclick = autoRun;