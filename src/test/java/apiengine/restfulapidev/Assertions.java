package apiengine.restfulapidev;

import org.testng.Assert;

import com.model.request.RequestItem;
import com.model.response.ResponseObject;

public class Assertions {
  public void assertAddProduct(ResponseObject responseObject, RequestItem requestItem) {
    Assert.assertNotNull(responseObject.id);
    Assert.assertEquals(responseObject.name, requestItem.name);
    Assert.assertEquals(responseObject.data.year, requestItem.data.year);
    Assert.assertEquals(responseObject.data.price, requestItem.data.price);
    Assert.assertEquals(responseObject.data.cpuModel, requestItem.data.cpuModel);
  }

  public void assertUpdateProduct(ResponseObject responseObject, RequestItem requestItem) {
    assertAddProduct(responseObject, requestItem);
    Assert.assertNotNull(responseObject.updatedAt);

  }
}
