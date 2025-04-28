<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All Establishments</title>

    <style>
        body {
            font-family: 'Orbitron', sans-serif;
            background-color: #0a0a2a;
            color: #00ffcc;
            margin: 0;
            padding: 0;
            text-align: center;
            min-height: 100vh;
        }

        .wrapper {
            width: 80%;
            max-width: 1000px;
            margin: 30px auto;
            padding: 20px;
            background: #1a0033;
            border-left: 10px solid #ff6600;
            border-right: 10px solid #ff6600;
            box-shadow: 0 0 15px rgba(255, 102, 0, 0.8);
            border-radius: 10px;
        }

        h1 {
            color: #ff00ff;
            text-shadow: 1px 1px 5px #ff6600;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background: #1a0033;
            border-radius: 10px;
            box-shadow: 0 0 15px #00ffcc;
            margin-top: 20px;
        }

        td.longNameColHeading,
        td.shortNameColHeading,
        td.addressColHeading {
            text-align: center;
            font-weight: bold;
            color: #ff00ff;
            background: #1a0033;
            padding: 10px;
            border-bottom: 2px solid #ff6600;
            text-shadow: none;
        }

        td.longNameCol,
        td.shortNameCol,
        td.addressCol {
            text-align: left;
            color: #00ffcc;
            background: #1a0033;
            font-weight: bold;
            padding: 10px 20px;
            border-bottom: 1px solid #00ffcc;
        }

        tr.establishmentRow {
            transition: background 0.3s ease;
        }

        tr.establishmentRow:hover {
            background: #220066;
        }

        button {
            background: #ff6600;
            color: white;
            border: none;
            padding: 12px 20px;
            border-radius: 5px;
            cursor: pointer;
            transition: background 0.3s ease, box-shadow 0.3s ease;
            text-shadow: 1px 1px 5px #000;
            box-shadow: 0 0 10px #ff6600;
            font-size: 16px;
            margin: 20px 10px 0;
        }

        button:hover {
            background: #ff3300;
            box-shadow: 0 0 15px #ff00ff;
        }

        .error {
            color: red;
            font-weight: bold;
            margin-bottom: 20px;
        }
    </style>
</head>

<body>

    <div class="wrapper">
        <c:if test="${! empty errorMessage}">
            <div class="error">${errorMessage}</div>
        </c:if>

        <h1>All Establishments</h1>

        <form action="${pageContext.servletContext.contextPath}/index" method="post">
            <table>
                <tr>
                    <td class="longNameColHeading">Long Name</td>
                    <td class="shortNameColHeading">Short Name</td>
                    <td class="addressColHeading">Address</td>
                </tr>

                <c:forEach items="${establishments}" var="establishment">
                    <tr class="establishmentRow">
                        <td class="longNameCol">${establishment.longname}</td>
                        <td class="shortNameCol">${establishment.shortname}</td>
                        <td class="addressCol">${establishment.address}</td>
                    </tr>
                </c:forEach>
            </table>

            <button type="submit" name="submithome">Home</button>
        </form>
    </div>

</body>
</html>
