# springboot-executor-kafka
使用kafka 0.9以上版本，0.9及之前版本，有很大的不同，API不同。
如果使用单进行消费并入库，可能会把数据库拖垮，所以在生产环境中，一般使用线程池方式进行入库操作。