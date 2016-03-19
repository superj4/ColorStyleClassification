library("e1071")
train<-read.csv("testFiles/train.csv")
test<-read.csv("testFiles/test.csv")
nb<-ncol(train)
id<-test[,1]
category<-as.character(train[,nb])
attributes<-train[,c(-1,-nb)]
testattrib<-test[,c(-1,0)]
model<-svm(attributes, category, type="C", probability = TRUE)
prediction<-predict(model, testattrib, probability = TRUE)
out<-cbind(id, as.character(prediction))
write.csv(out, file = "testFiles/svm.csv", row.names = FALSE)