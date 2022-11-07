<!-- markdownlint-disable-file first-line-h1 -->

#### General Implementation Details

The statistics command is implemented as a standalone command that ties together the `Item` and `Tag` class. By performing calculations, it returns relevant statistics to the user.

#### General Design Considerations

The `stats` command calculates and provides users a list of statistics relating to FoodRem's inventory. The list of statistics provided is as follows:

* Top three most expensive items
* Total amount of expired food
* Top three most common tags

#### Displaying Statistics

##### Overview
The `stats` command displays the calculated statistics based on the inventory by FoodRem.

##### Feature Details

1. The user calls the `stats` command.
1. FoodRem performs the necessary calculations to obtain the statistics. FoodRem then displays the result to the user.
1. If there are fewer than three items, or if there are fewer than three tags, FoodRem displays a placeholder "-".

##### Feature Considerations

The three statistics were chosen as a baseline because they are a good starting point for users to help track their food waste. For example, the user can obtain the total amount of wasted food, which is food whose expiry date has already been passed. Future extensions can include additional statistics, or even provide further arguments to selectively display desired statistics. For example, `stats topThreeMostExpensive` would display the statistics for the top three most expensive items only.
