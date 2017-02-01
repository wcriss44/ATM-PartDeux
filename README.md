![screenshot](http://25.media.tumblr.com/9a2463fccc5447ecad96f8a3fbf4713a/tumblr_mljtzfc1sZ1r4g1p5o1_400.gif)

## Description

Extend your ATM code to allow adding and removing bank accounts. Obviously, this is not typically a feature of ATMs, but we are innovators.

## Requirements

* Create a `HashMap` to store everyone's balances. It should map names (`String`) to balances (`Double`).
* When the user types a name that isn't recognized (i.e., isn't in the `HashMap`), offer to create an account for them. Then show them the screen listing the three options like before.
* Keep looping over the three options until the user cancels. The `HashMap` should keep its previously-set values when it loops around.
* Create a fourth option to remove the current user's bank account.
* When the user is done, loop back to the very beginning so it asks you for your name again. That way, other people can log in.


## HARDMODE
* Allow users to transfer funds to each other.


## LUDICROUSMODE
* Allow each user to have multiple bank accounts (e.g. "checking" and "savings")
