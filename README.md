a very simple one to one chat app using firebase realtime database and mvvm approach

MVVM involves a data binding approach to make code short and reduce view handling code from java classes.
View models are responsible to update view and once a view model is tied to a view then view get notified about their
updating events. If a model gets updated by a user click then model send callbacks to view model which updates
the view automatically as it gets tied to the views. Mvvm reduce code size but the implementation of 
MVVM is quite tough and it is difficult to debug big projects based on Mvvm. 
