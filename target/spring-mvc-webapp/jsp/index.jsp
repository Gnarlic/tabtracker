<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Tab Tracker</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
        <style>

            .light {

                color:#fff;
            }

            a {
                color:#fff;
            }

            a:hover {
                color:#fff;
                text-decoration:underline;
            }
            .navbar {
                background-color: #00419E;
                background-size:100%;
                z-index: 9999;
                border: 0;
                font-size: 12px !important;
                line-height: 1.42857143 !important;
                letter-spacing: 4px;
                border-radius: 0;

            }
            body {
                padding-bottom:600px;
            }
        </style>
    </head>
    <body style="background-color:#002D6E">
        <nav class="navbar navbar-dark fixed-bottom" style="display:inline-block;padding-top:30px">
            <div class="text-center light">

                <div class="row">
                    <div class="col-md-3"></div>
                    <div class="col-md-6">
                        <form class="row form-group form-group-md" action="${pageContext.request.contextPath}/search" method="GET">
                            <div class="col-md-6">
                                <select required class="form-control form-control-sm" id="category" name="category">
                                    <option value="" disabled selected hidden>Search For...</option>
                                    <option value="songName">Song Name</option>
                                    <option value="artist">Artist</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <input type="text" id="searchTerm" name="searchTerm" class="form-control form-control-sm" placeholder="Search Term" required="required">
                            </div>
                            <div class="col-md-2">
                                <button class="btn btn-sm btn-default" type="submit">Search</button>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-3"></div>

                </div>
                <div>Add Song</div>
                <div>
                    <sf:form class="form-horizontal" role="form" modelAttribute="tab"
                             method="POST" action="${pageContext.request.contextPath}/addTab">
                        <div class="row">
                            <div class="col-lg-3">
                                <label for="song-name" class="control-label">Song</label>
                                <sf:input type="text" id="song-name" name="songName" path="songName"/>
                            </div>
                            <div class="col-lg-3">
                                <label for="artist" class="control-label">Artist</label>
                                <sf:input type="text" id="artist" name="artist" path="artist"/>
                            </div>
                            <div class="col-lg-3">
                                <label for="url" class="control-label">URL</label>
                                <sf:input type="text" id="url" name="url" path="url"/>
                            </div>
                            <div class="col-lg-3">
                                <button class="btn btn-default" type="submit">Add Song</button>
                            </div>
                        </div>
                    </sf:form>
                </div>
        </nav>
        <div class="container" style="background-color:#0056D2;height:100vh;padding-top:50px">
            <div class="text-center light" style="margin-bottom:30px">
                <h1><a href="http://www.gnarlitronic.com/tabtracker">Tab Tracker</a></h1>
                <p>
                    Keep track of tabs for guitar.
                </p>
            </div>
            <div>
                <table class="table table-hover" style="">
                    <tr class="bg-primary light">
                        <th>
                            <h3>Song Name</h3>
                        </th>
                        <th>
                            <h3>Artist</h3>
                        </th>
                        <th>
                            <h3>URL</h3>
                        </th>
                        <th></th>
                    </tr>
                    <c:forEach var="tab" items="${tabList}">
                        <tr>
                            <td class="border-bottom border-dark light"><h4>${tab.songName}</h4></td>
                            <td class="border-bottom border-dark light"><h4>${tab.artist}</h4></td>
                            <td class="border-bottom border-dark light"><a href="${tab.url}"><h4>Link</h4></a></td>
                            <td class="border-bottom border-dark light"><a href="${pageContext.request.contextPath}/delete?id=${tab.id}">Delete</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>

