# buzz-micro-blogging

following this project 
https://github.com/tkouleris/Tweety

Create Tweet
POST : req  //send JWT bearer token in header

ALso upload media and get the media_id from there and provide it to media ID

"text":"This is text tweet <><><><>  test nested response THis is  test",
    "medias": [
        {
            "mediaId":5,
            "fileName":"Test.jpg",
            "fileType":"img"
        }

    ]
}

Response:


{
    "timestamp": [
        2022,
        12,
        19,
        19,
        46,
        14,
        685569000
    ],
    "message": "Tweet created!",
    "data": {
        "tweetId": 15,
        "text": "This is text tweet <><><><>  test nested response THis is  test",
        "created": 1671459371319,
        "updated": 1671459371319,
        "tweetAuthor": {
            "userId": 1,
            "username": "TEst",
            "password": "$2a$10$fYJSYNFKh4p61imDEUEIVe.WjccJD3PgdzHJIDYUhMUpfCfcO4gse",
            "email": "sankha@gmail.com",
            "followers": []
        },
        "replies": null,
        "likes": null,
        "medias": [
            {
                "mediaId": 5,
                "fileName": "Test.jpg",
                "fileType": "img",
                "created": null,
                "updated": null,
                "tweet": null
            }
        ]
    }
}
