---
layout: page
title: User Guide
---

## Features

### Adding an order: `addo`

Adds an order to the list of tracked orders.

Format: `addo i/ITEM_NAME q/ORDER_QUANTITY cn/CUSTOMER_NAME ca/CUSTOMER_ADDRESS ce/CUSTOMER_EMAIL cc/CUSTOMER_CONTACT`

* Adds an order to be tracked in the application
* Added orders will track the time that it was created

Examples:
* `addo i/Fountain Pen q/3 cn/John Doe ca/48 Westwood Terrace ce/johndoe@example.com cc/91234567`
* `addo i/White Socks q/2 cn/Betty White ca/39 Ocean Drive ce/bettywhite@example.com cc/92345678`

### Deleting an order: `deleteo`

Deletes an order from the list of tracked orders.

Format: `deleteo INDEX`

* Deletes the order at the specified INDEX.
* The index refers to the index number shown in the displayed order list.
* The index must be a positive integer 1, 2, 3, …

Examples:
* `listo` followed by `deleteo 2` deletes the 2nd order from the order list.

## Command summary

| Action              | Format, Examples                                                                                                                                                                                                          |
|---------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add an Order**    | `addo i/ITEM_NAME q/ORDER_QUANTITY cn/CUSTOMER_NAME ca/CUSTOMER_ADDRESS ce/CUSTOMER_EMAIL cc/CUSTOMER_CONTACT` <br> e.g., `addo i/Fountain Pen q/3 cn/John Doe ca/48 Westwood Terrace ce/johndoe@example.com cc/91234567` |
| **Delete an Order** | `deleteo INDEX` <br> e.g., `delete 2`                                                                                                                                                                                     |
