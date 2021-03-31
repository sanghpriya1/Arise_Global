
    $(document).ready(function () {
        $("button").click(function (event) {
            event.preventDefault();
            onPay()
        });
    });

    var awsUrl = "http://Paysafehelper-env.eba-pehyeyyj.us-west-2.elasticbeanstalk.com";
    var localUrl = "http://localhost:8080";
    //var localUrl = "http://18.222.211.178:8080"
    var baseUrl= localUrl;
    async function onPay() {
    	debugger;
        let email = document.getElementById("email").value;
        let firstName = document.getElementById("firstName").value;
        let lastName = document.getElementById("lastName").value;
        let phone = document.getElementById("inputPhone").value;
        //billing address
        let city = document.getElementById("inputCity").value;
        let zip = document.getElementById("inputZip").value;
        let street = document.getElementById("inputAddress").value;
        //amount
        let amount = document.getElementById("inputAmount").value;
        console.log("In pay function");
        $.ajax({
            url: baseUrl+"/SingleUseCustomerToken/" + email,
            type: "GET",
            contentType: "application/json",
            success: function (result) {
                if (result.status === "OK" && result.data != null) {
                    console.log("result of get single token:")
                    console.log(result);
                    billingAddress = {
                        city: city,
                        street: street,
                        zip: zip,
                        country: 'US',
                        state: 'CA'
                    }
                    customer = {
                        firstName: firstName,
                        lastName: lastName,
                        email: email,
                        phone: phone,
                        dateOfBirth: {
                            day: 1,
                            month: 6,
                            year: 1989
                        }
                    }
                    checkout(result.data.singleUseCustomerToken, billingAddress, customer, amount, result.data.merchantRefNum)
                }
                else {
                    alert("Please keep in mind -----" + result.message)
                }
            }
        });

    }

    function checkout(token, billingAddress, customer, amount, uuid) {
	debugger;
        console.log( "In Checkout function" );
        console.log( token );
        console.log( uuid );
        paysafe.checkout.setup("cHVibGljLTc3NTE6Qi1xYTItMC01ZjAzMWNiZS0wLTMwMmQwMjE1MDA4OTBlZjI2MjI5NjU2M2FjY2QxY2I0YWFiNzkwMzIzZDJmZDU3MGQzMDIxNDUxMGJjZGFjZGFhNGYwM2Y1OTQ3N2VlZjEzZjJhZjVhZDEzZTMwNDQ=", {
            "currency": "USD",
            "amount": parseInt(amount) * 100,
            "singleUseCustomerToken": token,

            "customer": customer,
            "billingAddress": billingAddress,
            "paymentMethodDetails": {
                "paysafecard": {
                    "consumerId": "1232323"
                },
            },
            "environment": "TEST",
      
            "merchantRefNum": uuid,
            "canEditAmount": false,
            "payout": false,
            "payoutConfig": {
                "maximumAmount": 10000
            }
        }, function (instance, error, result) {

            console.log( "in call back function" );
            if (result && result.paymentHandleToken) {

                console.log( result.paymentHandleToken );
                console.log("result of checkout")
                console.log(result);
                requestBody = {
                    "merchantRefNum": uuid,
                    "paymentHandleToken": result.paymentHandleToken,
                    "amount": result.amount,
                    "settleWithAuth": false,
                    "currencyCode": "USD",
                    "dupCheck": true,
                    "description": "Casino subscription"
                }
                debugger;
                console.log("request body for payment process")
                console.log(requestBody)
                $.ajax({
                    type: "POST",
                    url: baseUrl+"/MakePayment/" + customer.email,
                    contentType: "application/json",
                    data: JSON.stringify(requestBody,),
                    success: (data) => {
                        console.log("result of process payment:")
                        console.log(data);

                        if (data.status === "OK" && data.data != null) {
                            instance.showSuccessScreen("Payment Done Successfully!");
                        }
                        else {
                            instance.showFailureScreen("Payment was declined. Try again with the same or another payment method.");
                        }
                        setTimeout(function(){window.location.replace(window.location.href);}, 10000);

                    }
                });
            }
            else {
                alert("Please keep in mind -----" + error.detailedMessage)
                console.error("error :" + error);
            }
        }, function (stage, expired) {
            switch (stage) {
                case "PAYMENT_HANDLE_NOT_CREATED": // Handle the scenario
                case "PAYMENT_HANDLE_CREATED": // Handle the scenario
                case "PAYMENT_HANDLE_REDIRECT": // Handle the scenario
                case "PAYMENT_HANDLE_PAYABLE": // Handle the scenario
                default: // Handle the scenario
            }
        });
    }
