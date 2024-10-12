<%--
  Created by IntelliJ IDEA.
  User: vanph
  Date: 9/12/2024
  Time: 1:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="main">
    <div class="container">
        <div class="col-md-9 col-sm-9">
            <h1>Login</h1>
            <div class="content-form-page">
                <div class="row">
                    <div class="col-md-7 col-sm-7">
                        <form action="/login" method="post" class="form-horizontal form-without-legend" role="form">
                            <div class="form-group">
                                <label for="username" class="col-lg-4 control-label">Username <span class="require">*</span></label>
                                <div class="col-lg-8">
                                    <input type="text" class="form-control" name="username" id="username">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-lg-4 control-label">Password <span class="require">*</span></label>
                                <div class="col-lg-8">
                                    <input type="password" class="form-control" name="password" id="password">
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-8 col-md-offset-4 padding-left-0">
                                    <a href="page-forgotton-password.html">Forget Password?</a>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-8 col-md-offset-4 padding-left-0 padding-top-20">
                                    <button type="submit" class="btn btn-primary">Login</button>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-8 col-md-offset-4 padding-left-0 padding-top-10 padding-right-30">
                                    <hr>
                                    <div class="login-socio">
                                        <p class="text-muted">or login using:</p>
                                        <ul class="social-icons">
                                            <li><a href="#" data-original-title="facebook" class="facebook" title="facebook"></a></li>
                                            <li><a href="#" data-original-title="Twitter" class="twitter" title="Twitter"></a></li>
                                            <li><a href="#" data-original-title="Google Plus" class="googleplus" title="Google Plus"></a></li>
                                            <li><a href="#" data-original-title="Linkedin" class="linkedin" title="LinkedIn"></a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</br>