package com.axelromero.garba.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GarbaItemReviewModel {

    public List<ReviewItem> items;

    public class ReviewItem{
        public String id;
        @SerializedName("review_statistics")
        public ReviewStatistics reviewStatistics;
        public List<Reviews> reviews;
        @SerializedName("total_review_count")
        public int totalReviewCount;

        public class ReviewStatistics {

            @SerializedName("average_overall_rating")
            public double averageOverallRating;
            @SerializedName("rating_distribution")
            public List<RatingDistribution> ratingDistribution;

            public class RatingDistribution{
                @SerializedName("rating_value")
                public int ratingValue;
                public int count;
            }

        }

        public class Reviews {

            public String id;
            public String usernickname;
            public String title;
            @SerializedName("review_text")
            public String reviewText;
            public int rating;
            @SerializedName("submission_time")
            public String submissionTime;
            @SerializedName("product_id")
            public String productId;
        }
    }

}
