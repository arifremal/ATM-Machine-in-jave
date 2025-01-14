ATM Machine Program Overview
This Java program simulates an ATM machine with the following features:

Check Balance: View the current balance in the account.
Withdraw Money: Withdraw a specified amount, ensuring sufficient funds.
Deposit Money: Add funds to the account.
PIN Verification: Secure the account using a 4-digit PIN.
Key Concepts Demonstrated
Abstraction:
The ATM abstract class provides a blueprint for ATM operations.
Encapsulation:
The Account class secures account data (balance and PIN) with private fields and provides controlled access through getter/setter methods.
Inheritance:
The ATMImplementation class inherits the ATM abstract class and implements its methods.
Polymorphism:
The ATM class allows us to use a single interface for various ATM implementations.
How It Works
Account Initialization:
An account is created with an initial balance and PIN.
Menu Options:
Users can select options to check balance, withdraw, deposit, or exit.
PIN Validation:
Users must enter the correct PIN to access ATM functionalities.
Error Handling:
The program checks for invalid PINs, insufficient funds, and invalid menu options.
