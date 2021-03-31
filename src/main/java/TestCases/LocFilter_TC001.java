package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import AppModules.Deals;
import AppModules.Share;
import AppModules.TestActions;
import Utils.*;

public class LocFilter_TC001 extends SetUp {
	@Test
	public void main() throws Exception {

		String sTestCaseID = "LocFilter_TC001";
		test = extent.createTest(sTestCaseID);
		getTestCaseExpectedResult(sTestCaseID);
		sScreenShotTCFolder = createfolder(sScreenShotFolder, sTestCaseID);
		boolean bResult = false;

		try {
			// Launch application
			TestActions.LaunchApplication();

			// Login as Global Admin
			bResult = TestActions.Login(sTestCaseID);
			if (bResult == true) {
				bResult = Deals.addNewDeal(sTestCaseID);
				if (bResult == true) {
					// handle newly created deal widget
					Deals.DealWidget(sTestCaseID);
					// share to contact
					bResult = Share.ShareDeal(sTestCaseID);
					if (bResult == true) {
						bResult = false;

						bResult = Deals.DealWidget("LocFilter_TC001(2)");
						if (bResult == true) {
							// location outbound filter
							bResult = Deals.filterLocation(sTestCaseID);
							if (bResult == true) {
								// view deal in opportunity
								// select deal
								bResult = Deals.DealWidget("LocFilter_TC001(3)");
								if (bResult == true) {
									bResult = Deals.DealWidget("LocFilter_TC001(4)");
									// Reset filter
									bResult = Deals.filterLocation("LocFilter_TC001(2)");

									if (bResult == true) {
										bResult = false;
										// location return filter
										//Thread.sleep(5000);
										bResult = Deals.filterLocation("LocFilter_TC001(3)");

										if (bResult == true) {
											bResult = false;
											// select deal
											bResult = Deals.DealWidget("LocFilter_TC001(5)");
											if (bResult == true) {
												bResult = false;
												// view deal
												bResult = Deals.DealWidget("LocFilter_TC001(6)");
												if (bResult == true) {
													bResult = false;
													// Reset filter
													bResult = Deals.filterLocation("LocFilter_TC001(4)");
													//filter round trip
													bResult = Deals.filterLocation("LocFilter_TC001(5)");
													if (bResult == true) {
														bResult = false;
														// select deal
														bResult = Deals.DealWidget("LocFilter_TC001(7)");
														if (bResult == true) {
															bResult = false;
															// view deal
															bResult = Deals.DealWidget("LocFilter_TC001(8)");
															// Reset filter
															bResult = Deals.filterLocation("LocFilter_TC001(6)");
															if (bResult == true) {
																TestActions.LogOut();
																sActualResult = "Deal has been filtered";

															}

														}

													}
												}
											}

										}

									}
								}

							}

						}

						// sActualResult="Deal shared successfully";
					}

				}

			}
		} catch (Exception error) {
			sActualResult = error.getMessage();
		}

		TestActions.CloseApplication();
		Assert.assertEquals(sActualResult.toUpperCase().trim(), sTestCaseExpectedResult.toUpperCase().trim());
		// ResultComparision();

	}
}
