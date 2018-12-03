/**
 * 
 * @author Sabyasachi.Gu
 * 
 * Once we required to integrate and aggregate data data from various systems and DB.
 * We had an SOA architecture with Rest and SOAP. 
 * We were using a SSO and two way SSL for outside and inside servers.
 * Also we had DBs for different systems from which we required to collect the data.
 * The requirement was to collate the data... So we thought why not we collect the data in parallel 
 * and then when every thread completes its tack, we do the aggregation.
 * 
 * So, a framework was developed to execute tasks in parallel is a distributed environment before aggregating or collating the results.
 * 
 * Below implementation shows hypothetical SOA and DB processors with CountDownLatch. 
 * There is no logic in the processors but the framework that we developed can be easily accomplished to 
 * do any number of parallel tasks which needs to be completed before proceeding with next task.
 * 
 * And of course You need to have the same kind of Input and result object
 * 
 * Worker threads first bring in the data(do the job in parallel) run by Executor Service based on a countdown latch
 * 
 */
