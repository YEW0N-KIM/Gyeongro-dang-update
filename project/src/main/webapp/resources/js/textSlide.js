//jsp 내부에서는 움직이는 css효과가 적용이 안되서 텍스트 슬라이드 js코드만 따로 뺐습니다

document.addEventListener("DOMContentLoaded", function() {
    var Con = document.querySelectorAll('.content');
    
    Con.forEach(function(item) {
        console.log("test");
        const textBox = item.querySelector('.info-name');
        const text = item.querySelector('.info-name span');

        if (textBox && text) {
            const textBoxW = textBox.offsetWidth;
            const textW = text.offsetWidth;
            
            const wpqkftq = textBoxW - textW;
            
            item.addEventListener('mouseenter', function() {
                console.log("textBoxW : " + textBoxW);
                console.log("textW : " + textW);
                console.log("wpqkftq : " + wpqkftq);

                if (wpqkftq < 0) {
                    text.style.transform = `translateX(${wpqkftq}px)`;
                    
                } else {
                    text.style.transform = `translateX(0px)`;
                }
            });

            item.addEventListener('mouseleave', function() {
                text.style.transform = `translateX(0px)`;
            });
        }
    });
});