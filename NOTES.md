# Notes

Please add here any notes, assumptions and design decisions that might help up understand your though process.

## Assumptions/Drawbacks
1. The solution assumes all discounts are based on specific items in the basket rather than the total sum of the basket e.g. how to handle such cases as "get £5 off when you spend £25 or more"?
2. There is some duplication of code that due to time constraints have not been removed
3. The solution stores discounts as a list against products - all discounts will be applied in turn. This is probably not a realistic way of stacking discounts.
4. Not all of the examples in the README have been implemented due to time constraints.
