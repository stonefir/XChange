package org.knowm.xchange.quadrigacx;

import org.knowm.xchange.quadrigacx.dto.QuadrigaCxException;
import org.knowm.xchange.quadrigacx.dto.account.QuadrigaCxBalance;
import org.knowm.xchange.quadrigacx.dto.account.QuadrigaCxDepositAddress;
import org.knowm.xchange.quadrigacx.dto.trade.QuadrigaCxOrder;
import org.knowm.xchange.quadrigacx.dto.trade.QuadrigaCxUserTransaction;
import org.knowm.xchange.quadrigacx.service.QuadrigaCxDigest;
import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.math.BigDecimal;

@Path("v2")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.APPLICATION_JSON)
public interface QuadrigaCxAuthenticated {

  @POST
  @Path("open_orders")
  public QuadrigaCxOrder[] getOpenOrders(@FormParam("book") String book, @FormParam("key") String apiKey, @FormParam("signature") ParamsDigest signer,
                                         @FormParam("nonce") SynchronizedValueFactory<Long> nonce) throws QuadrigaCxException, IOException;

  @POST
  @Path("buy")
  public QuadrigaCxOrder buy(@FormParam("book") String book, @FormParam("key") String apiKey, @FormParam("signature") ParamsDigest signer,
      @FormParam("nonce") SynchronizedValueFactory<Long> nonce, @FormParam("amount") BigDecimal amount, @FormParam("price") BigDecimal price)
      throws QuadrigaCxException, IOException;

  @POST
  @Path("sell")
  public QuadrigaCxOrder sell(@FormParam("book") String book, @FormParam("key") String apiKey, @FormParam("signature") ParamsDigest signer,
      @FormParam("nonce") SynchronizedValueFactory<Long> nonce, @FormParam("amount") BigDecimal amount, @FormParam("price") BigDecimal price)
      throws QuadrigaCxException, IOException;

  /**
   * @return true if order has been canceled.
   */
  @POST
  @Path("cancel_order")
  public boolean cancelOrder(@FormParam("key") String apiKey, @FormParam("signature") ParamsDigest signer,
      @FormParam("nonce") SynchronizedValueFactory<Long> nonce, @FormParam("id") String orderId) throws QuadrigaCxException, IOException;

  @POST
  @Path("user_transactions")
  public QuadrigaCxUserTransaction[] getUserTransactions(@FormParam("book") String book, @FormParam("key") String apiKey, @FormParam("signature") ParamsDigest signer,
      @FormParam("nonce") SynchronizedValueFactory<Long> nonce, @FormParam("limit") long numberOfTransactions) throws QuadrigaCxException, IOException;

  @POST
  @Path("user_transactions")
  public QuadrigaCxUserTransaction[] getUserTransactions(@FormParam("book") String book, @FormParam("key") String apiKey, @FormParam("signature") ParamsDigest signer,
      @FormParam("nonce") SynchronizedValueFactory<Long> nonce, @FormParam("limit") long numberOfTransactions, @FormParam("offset") long offset,
      @FormParam("sort") String sort) throws QuadrigaCxException, IOException;

  @POST
  @Path("balance")
  QuadrigaCxBalance getBalance(@FormParam("key") String apiKey, @FormParam("signature") QuadrigaCxDigest signer,
      @FormParam("nonce") SynchronizedValueFactory<Long> nonce) throws QuadrigaCxException, IOException;

  @POST
  @Path("bitcoin_deposit_address")
  QuadrigaCxDepositAddress getBitcoinDepositAddress(@FormParam("key") String apiKey, @FormParam("signature") QuadrigaCxDigest signer,
      @FormParam("nonce") SynchronizedValueFactory<Long> nonce) throws QuadrigaCxException, IOException;

  @POST
  @Path("ether_deposit_address")
  QuadrigaCxDepositAddress getEtherDepositAddress(@FormParam("key") String apiKey, @FormParam("signature") QuadrigaCxDigest signer,
                                                    @FormParam("nonce") SynchronizedValueFactory<Long> nonce) throws QuadrigaCxException, IOException;

  @POST
  @Path("bitcoin_withdrawal")
  String withdrawBitcoin(@FormParam("key") String apiKey, @FormParam("signature") QuadrigaCxDigest signer,
      @FormParam("nonce") SynchronizedValueFactory<Long> nonce, @FormParam("amount") BigDecimal amount, @FormParam("address") String address)
      throws QuadrigaCxException, IOException;

  @POST
  @Path("ether_withdrawal")
  String withdrawEther(@FormParam("key") String apiKey, @FormParam("signature") QuadrigaCxDigest signer,
      @FormParam("nonce") SynchronizedValueFactory<Long> nonce, @FormParam("amount") BigDecimal amount, @FormParam("address") String address)
      throws QuadrigaCxException, IOException;
}
