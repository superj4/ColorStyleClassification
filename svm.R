train<-read.csv("train.csv")
test<-read.csv("test.csv")
nb<-ncol(train)
id<-test[,1]
category<-as.character(train[,nb])
attributes<-train[,c(-1,-nb)]
testattrib<-test[,c(-1,0)]
model<-svm(attributes, category, type="C", probability = TRUE)
prediction<-predict(model, testattrib, probability = TRUE)
out<-cbind(id, as.character(prediction))
write.csv(out, file = "svm.csv", row.names = FALSE)