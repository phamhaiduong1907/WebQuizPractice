
<!DOCTYPE html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home Page</title>
        <!-- FontAwesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css"
              integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <!-- Bootstrap's CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link rel="stylesheet" href="css/global.css">
        <link rel="stylesheet" href="css/header.css">
        <link rel="stylesheet" href="css/popup.css">
        <link rel="stylesheet" href="css/footer.css">
        <link rel="stylesheet" href="css/index.css">
        

    </head>

    <body>
        <header>
            <div class="heading_logo">
                <p>LOGO</p>
            </div>
            <nav>
                <ul class="nav_links">
                    <li><a href="index.html">Home</a></li>
                    <li><a href="view/subject/subjectlist.html">Subject</a></li>
                    <li><a href="view/blog/list.html">Blog</a></li>
                    <li><a href="#" class="login" id="loginButton">Log in</a></li>
                </ul>
            </nav>
        </header>
        
        
        <section class="slider">
            <div id="slider" class="carousel carousel-dark slide" data-bs-ride="carousel">
                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#slider" data-bs-slide-to="0" class="active" aria-current="true"
                            aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#slider" data-bs-slide-to="1" aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#slider" data-bs-slide-to="2" aria-label="Slide 3"></button>
                </div>
                <div class="carousel-inner">
                    <div class="carousel-item active" data-bs-interval="5000">
                        <a href="#">
                            <div class="block__item"></div>
                            <div class="carousel-caption d-none d-md-block">
                                <h5>First slide label</h5>
                                <p>Some representative placeholder content for the first slide.</p>
                            </div>
                        </a>
                    </div>
                    <div class="carousel-item" data-bs-interval="5000">
                        <a href="#">
                            <div class="block__item"></div>
                            <div class="carousel-caption d-none d-md-block">
                                <h5>Second slide label</h5>
                                <p>Some representative placeholder content for the second slide.</p>
                            </div>
                        </a>
                    </div>
                    <div class="carousel-item">
                        <a href="#">
                            <div class="block__item"></div>
                            <div class="carousel-caption d-none d-md-block">
                                <h5>Third slide label</h5>
                                <p>Some representative placeholder content for the third slide.</p>
                            </div>
                        </a>
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#slider" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#slider" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
        </section>

        <section class="post">
            <h1>HOT POSTS</h1>
            <div class="post__wrapper">
                <div class="post__item">
                    <div class="post__short">
                        <p>Date: 14/05/2022</p>
                    </div>
                    <div class="post__info">
                        <div class="post__thumbnail">
                            <p>THUMBNAIL</p>
                        </div>
                        <div class="post__title">
                            <p>Title of the post: Lorem, ipsum dolor.</p>
                        </div>
                    </div>
                    <div class="post__detail">
                        <a href="view/blog/detail.html">View Detail <i class="fa fa-arrow-circle-right"></i></a>
                    </div>
                </div>
                <div class="post__item">
                    <div class="post__short">
                        <p>Date: 14/05/2022</p>
                    </div>
                    <div class="post__info">
                        <div class="post__thumbnail">
                            <p>THUMBNAIL</p>
                        </div>
                        <div class="post__title">
                            <p>Title of the post: Lorem, ipsum dolor.</p>
                        </div>
                    </div>
                    <div class="post__detail">
                        <a href="view/blog/detail.html">View Detail <i class="fa fa-arrow-circle-right"></i></a>
                    </div>
                </div>
                <div class="post__item">
                    <div class="post__short">
                        <p>Date: 14/05/2022</p>
                    </div>
                    <div class="post__info">
                        <div class="post__thumbnail">
                            <p>THUMBNAIL</p>
                        </div>
                        <div class="post__title">
                            <p>Title of the post: Lorem, ipsum dolor.</p>
                        </div>
                    </div>
                    <div class="post__detail">
                        <a href="view/blog/detail.html">View Detail <i class="fa fa-arrow-circle-right"></i></a>
                    </div>
                </div>
            </div>
        </section>

        <section class="subject">
            <h1>POPULAR SUBJECT</h1>
            <button class="pre-btn"><img src="images/arrow.png" alt=""></button>
            <button class="nxt-btn"><img src="images/arrow.png" alt=""></button>
            <div class="subject__container">
                <div class="subject__content">

                    <div class="subject__card">
                        <div class="subject__card-content">
                            <div class="subject__thumnail post__thumbnail">
                                <p>THUMBNAIL</p>
                            </div>
                            <div class="subject__title post__title">
                                <p>Title of the subject: Lorem, ipsum dolor.</p>
                            </div>
                            <div class="subject__description">
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quia provident veniam enim
                                    accusantium? Quas, dolores. Optio maxime, sed aut repudiandae, provident explicabo ipsa
                                    natus corrupti dicta delectus doloremque! Non, a.</p>
                            </div>
                        </div>
                        <div class="post__detail">
                            <a href="view/subject/subjectdetail.html">View Detail <i
                                    class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                    <div class="subject__card">
                        <div class="subject__card-content">
                            <div class="subject__thumnail post__thumbnail">
                                <p>THUMBNAIL</p>
                            </div>
                            <div class="subject__title post__title">
                                <p>Title of the subject: Lorem, ipsum dolor.</p>
                            </div>
                            <div class="subject__description">
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quia provident veniam enim
                                    accusantium? Quas, dolores. Optio maxime, sed aut repudiandae, provident explicabo ipsa
                                    natus corrupti dicta delectus doloremque! Non, a.</p>
                            </div>
                        </div>
                        <div class="post__detail">
                            <a href="view/subject/subjectdetail.html">View Detail <i
                                    class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                    <div class="subject__card">
                        <div class="subject__card-content">
                            <div class="subject__thumnail post__thumbnail">
                                <p>THUMBNAIL</p>
                            </div>
                            <div class="subject__title post__title">
                                <p>Title of the subject: Lorem, ipsum dolor.</p>
                            </div>
                            <div class="subject__description">
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quia provident veniam enim
                                    accusantium? Quas, dolores. Optio maxime, sed aut repudiandae, provident explicabo ipsa
                                    natus corrupti dicta delectus doloremque! Non, a.</p>
                            </div>
                        </div>
                        <div class="post__detail">
                            <a href="view/subject/subjectdetail.html">View Detail <i
                                    class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                    <div class="subject__card">
                        <div class="subject__card-content">
                            <div class="subject__thumnail post__thumbnail">
                                <p>THUMBNAIL</p>
                            </div>
                            <div class="subject__title post__title">
                                <p>Title of the subject: Lorem, ipsum dolor.</p>
                            </div>
                            <div class="subject__description">
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quia provident veniam enim
                                    accusantium? Quas, dolores. Optio maxime, sed aut repudiandae, provident explicabo ipsa
                                    natus corrupti dicta delectus doloremque! Non, a.</p>
                            </div>
                        </div>
                        <div class="post__detail">
                            <a href="view/subject/subjectdetail.html">View Detail <i
                                    class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                    <div class="subject__card">
                        <div class="subject__card-content">
                            <div class="subject__thumnail post__thumbnail">
                                <p>THUMBNAIL</p>
                            </div>
                            <div class="subject__title post__title">
                                <p>Title of the subject: Lorem, ipsum dolor.</p>
                            </div>
                            <div class="subject__description">
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quia provident veniam enim
                                    accusantium? Quas, dolores. Optio maxime, sed aut repudiandae, provident explicabo ipsa
                                    natus.</p>
                            </div>
                        </div>
                        <div class="post__detail">
                            <a href="view/subject/subjectdetail.html">View Detail <i
                                    class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                    <div class="subject__card">
                        <div class="subject__card-content">
                            <div class="subject__thumnail post__thumbnail">
                                <p>THUMBNAIL</p>
                            </div>
                            <div class="subject__title post__title">
                                <p>Title of the subject: Lorem, ipsum dolor.</p>
                            </div>
                            <div class="subject__description">
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quia provident veniam enim
                                    accusantium? Quas, dolores. </p>
                            </div>
                        </div>
                        <div class="post__detail">
                            <a href="view/subject/subjectdetail.html">View Detail <i
                                    class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                    <div class="subject__card">
                        <div class="subject__card-content">
                            <div class="subject__thumnail post__thumbnail">
                                <p>THUMBNAIL</p>
                            </div>
                            <div class="subject__title post__title">
                                <p>Title of the subject: Lorem, ipsum dolor.</p>
                            </div>
                            <div class="subject__description">
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quia provident veniam enim
                                    accusantium? Quas, dolores. Optio maxime, sed aut repudiandae, provident explicabo ipsa
                                    natus corrupti dicta delectus doloremque! Non, a.</p>
                            </div>
                        </div>
                        <div class="post__detail">
                            <a href="view/subject/subjectdetail.html">View Detail <i
                                    class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                    <div class="subject__card">
                        <div class="subject__card-content">
                            <div class="subject__thumnail post__thumbnail">
                                <p>THUMBNAIL</p>
                            </div>
                            <div class="subject__title post__title">
                                <p>Title of the subject: Lorem, ipsum dolor.</p>
                            </div>
                            <div class="subject__description">
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quia provident veniam enim
                                    accusantium? Quas, dolores. Optio maxime, sed aut repudiandae, provident explicabo ipsa
                                    natus corrupti dicta delectus doloremque! Non, a.</p>
                            </div>
                        </div>
                        <div class="post__detail">
                            <a href="view/subject/subjectdetail.html">View Detail <i
                                    class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                    <div class="subject__card">
                        <div class="subject__card-content">
                            <div class="subject__thumnail post__thumbnail">
                                <p>THUMBNAIL</p>
                            </div>
                            <div class="subject__title post__title">
                                <p>Title of the subject: Lorem, ipsum dolor.</p>
                            </div>
                            <div class="subject__description">
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quia provident veniam enim
                                    accusantium? Quas, dolores. Optio maxime, sed aut repudiandae, provident explicabo ipsa
                                    natus corrupti dicta delectus doloremque! Non, a.</p>
                            </div>
                        </div>
                        <div class="post__detail">
                            <a href="view/subject/subjectdetail.html">View Detail <i
                                    class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                    <div class="subject__card">
                        <div class="subject__card-content">
                            <div class="subject__thumnail post__thumbnail">
                                <p>THUMBNAIL</p>
                            </div>
                            <div class="subject__title post__title">
                                <p>Title of the subject: Lorem, ipsum dolor.</p>
                            </div>
                            <div class="subject__description">
                                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quia provident veniam enim
                                    accusantium? Quas, dolores. Optio maxime, sed aut repudiandae, provident explicabo ipsa
                                    natus corrupti dicta delectus doloremque! Non, a.</p>
                            </div>
                        </div>
                        <div class="post__detail">
                            <a href="view/subject/subjectdetail.html">View Detail <i
                                    class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>

                </div>
            </div>
        </section>

        <section class="popup">
            <div class="popup__content">
                <img src="${pageContext.request.contextPath}/images/close.png" alt="" class="close">

                <div class="popup__login-form">
                    <h2>Welcome to Quiz Practice</h2>
                    <div class="form__login">
                        <form action="login" method="POST">
                            <input type="text" name="email" id="emailLogin" placeholder="Enter your email" required>
                            <input type="password" name="password" id="password" placeholder="Enter your password" required>
                            <div class="popup__reset">
                                <a href="#">Forgot password?</a>
                            </div>
                            <div class="form__button">
                                <button type="submit">Login</button>
                            </div>
                        </form>
                    </div>

                    <div class="popup__signup">
                        <a href="#">Don't have any account? Sign up here</a>
                    </div>
                </div>

                <div class="popup__signup-form" style="display: none;">
                    <i class="fa fa-arrow-left"></i>
                    <h2>Register for Quiz Practice</h2>
                    <div class="form__signup">
                        <form action="register" method="POST">
                            <input type="text" name="firstName" id="firstName" pattern="[a-zA-Z]+" title="Must be letters" placeholder="First Name" required>
                            <input type="text" name="lastName" id="lastName" pattern="[a-zA-Z]+" title="Must be letters" placeholder="Last Name" required>
                            <div class="signup__gender">
                                <h5>Gender</h5>
                                <input type="radio" name="gender" value="male" required>Male
                                <input type="radio" name="gender" value="female" required>Female
                            </div>
                            <input type="text" name="email" id="emailSignup" pattern="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$" title="Must be in email format (eg: abc@xyz.com)" placeholder="Email" required>
                            <input type="text" name="phone" id="phone" pattern="[0-9]{9,10}" title="Must be between 9 and 10 digit" placeholder="Phone Number" required>
                            <input type="password" name="password" id="password"  placeholder="Password" required>
                            <input type="password" name="confirmPassword" id="confirmPassword"
                                   placeholder="Confirm password" required>
                            <div class="form__button">
                                <button type="submit">Register</button>
                            </div>
                        </form>
                    </div>
                </div>

                <div class="popup__reset-form" style="display: none;">
                    <i class="fa fa-arrow-left"></i>
                    <h2>Reset Password</h2>
                    <div class="form__reset">
                        <form action="forgotPassword">
                            <input type="text" name="email" id="emailReset"
                                   placeholder="Enter your email to reset your password">
                            <div class="form__button">
                                <button type="submit">Verify your email</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>

        <footer>
            <p>COPYRIGHT</p>
        </footer>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
        crossorigin="anonymous"></script>
        <script src="js/script.js"></script>
        <!-- Initialize Swiper -->
        <script src="js/swiper.js"></script>
    </body>

</html>