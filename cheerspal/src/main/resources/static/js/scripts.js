/*!
* Start Bootstrap - Clean Blog v6.0.9 (https://startbootstrap.com/theme/clean-blog)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-clean-blog/blob/master/LICENSE)
*/
window.addEventListener('DOMContentLoaded', () => {
    let scrollPos = 0;
    const mainNav = document.getElementById('mainNav');
    const headerHeight = mainNav.clientHeight;
    window.addEventListener('scroll', function() {
        const currentTop = document.body.getBoundingClientRect().top * -1;
        if ( currentTop < scrollPos) {
            // Scrolling Up
            if (currentTop > 0 && mainNav.classList.contains('is-fixed')) {
                mainNav.classList.add('is-visible');
            } else {
                console.log(123);
                mainNav.classList.remove('is-visible', 'is-fixed');
            }
        } else {
            // Scrolling Down
            mainNav.classList.remove(['is-visible']);
            if (currentTop > headerHeight && !mainNav.classList.contains('is-fixed')) {
                mainNav.classList.add('is-fixed');
            }
        }
        scrollPos = currentTop;
    });
});

function validateForm() {
    let valid = true;

    document.querySelectorAll('.is-invalid').forEach((el) => {
        el.classList.remove('is-invalid');
    });

    const name = document.getElementById('name');
    if (!name.value.trim()) {
        name.classList.add('is-invalid');
        valid = false;
    }

    const email = document.getElementById('email');
    if (!email.value.trim() || !email.value.match(/^[^\s@]+@[^\s@]+\.[^\s@]+$/)) {
        email.classList.add('is-invalid');
        valid = false;
    }

    const phone = document.getElementById('phone');
    if (!phone.value.trim()) {
        phone.classList.add('is-invalid');
        valid = false;
    }

    const message = document.getElementById('message');
    if (!message.value.trim()) {
        message.classList.add('is-invalid');
        valid = false;
    }

    return valid;
}

