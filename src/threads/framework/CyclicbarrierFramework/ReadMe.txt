After we did the multiple fetch from different systems, we got the requirement to update the collated data into those systems.

So, we used the CyclicBarrier .

1. Worker Threads gets the data (Run by Executor Service) and awaits on Cyclic barrier. 
2. Then the data was messaged and then stored in our DB by the cyclic barrier thread.
3. Then the data was sent back to the source systems by the worker threads.