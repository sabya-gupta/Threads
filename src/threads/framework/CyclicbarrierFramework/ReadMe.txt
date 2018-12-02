After we did the multiple fetch from different systems, we got the requirement to update the collated data into those systems.

So, we used the CyclicBarrier .

1. It gets the data. 
2. Then the data was first stored in our DB.
3. then the data was sent back to the source systems.