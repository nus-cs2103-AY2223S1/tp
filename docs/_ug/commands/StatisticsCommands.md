<!-- markdownlint-disable-file first-line-h1 -->
#### Display statistics: `stats` 

<!-- TODO: Update after Richard's PR on statistics -->

**Format**: `stats`

> Displays relevant statistics collected by FoodRem.

```note
These statistics include:
* Items that expire within the next 10 days
* Total cost of wasted items
* Most expensive item in your inventory
```

**Example Input:**

```text
stats
```

**Example Output:**

Command Output Box:

```text
Items that expire within the next 10 days:
1. Onions 8 kg $1 (Bought Date: 10-10-2022) (Expiry Date: 10-11-2022)
2. Chicken 30 kg $4.20 (Bought Date: 10-10-2022) (Expiry Date: 15-10-2022)
3. Carrots 11 kg $0.60 (Bought Date: 10-10-2022) (Expiry Date: 26-10-2022)

Total cost of wasted items:
$120

Most expensive item in your inventory:
Chicken 30 kg $4.20 (Bought Date: 10-10-2022) (Expiry Date: 15-10-2022)

```
