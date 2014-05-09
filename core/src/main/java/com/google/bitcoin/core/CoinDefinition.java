package com.google.bitcoin.core;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA. User: HashEngineering Date: 8/13/13 Time: 7:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class CoinDefinition {

	public static final String coinName = "Templecoin";
	public static final String coinTicker = "TPC";
	public static final String coinURIScheme = "templecoin";
	public static final String cryptsyMarketId = "36";
	public static final String cryptsyMarketCurrency = "BTC";

	public static final String BLOCKEXPLORER_BASE_URL_PROD = "http://block.templecoin.com/TP0/";
	public static final String BLOCKEXPLORER_BASE_URL_TEST = "http://block.templecoin.com/TPC/";

	public static final String DONATION_ADDRESS = "TDYbmJP3GTb5uqSkEUvGirLSD8GBVxvBfP"; // HashEngineering
																						// donation
																						// DGC
																						// address

	enum CoinHash {
		SHA256, scrypt
	};

	public static final CoinHash coinHash = CoinHash.scrypt;
	// Original Values
	public static final int TARGET_TIMESPAN_0 = (int) (4 * 60 * 60); // TempleCoin:
																		// every
																		// 4
																		// hourscycle,
																		// on
																		// average.
	public static final int TARGET_SPACING_0 = (int) (60); // TempleCoin: 1
															// minutes
	public static final int INTERVAL_0 = TARGET_TIMESPAN_0 / TARGET_SPACING_0; // 2016
																				// blocks

	public static final int TARGET_TIMESPAN = (int) (60 * 9); // 22.5 minutes
																// per
																// difficulty
																// cycle, on
																// average.
	public static final int TARGET_SPACING = (int) (60); // 2.5 minutes per
															// block.
	public static final int INTERVAL = TARGET_TIMESPAN / TARGET_SPACING; // 9
																			// blocks

	public static final int TARGET_TIMESPAN_3 = 60; // 22.5 minutes per
													// difficulty cycle, on
													// average.
	public static final int TARGET_SPACING_3 = (int) (60); // 2.5 minutes per
															// block.
	public static final int INTERVAL_3 = TARGET_TIMESPAN_3 / TARGET_SPACING_3; // 9
																				// blocks

	static final long nTargetSpacing = 60; // 2.5 minutes
	static final long nOriginalInterval = 2016;
	static final long nFilteredInterval = 9;
	static final long nOriginalTargetTimespan = nOriginalInterval
			* nTargetSpacing; // 3.5 days
	static final long nFilteredTargetTimespan = nFilteredInterval
			* nTargetSpacing; // 22.5 minutes

	public static int DIFF_FILTER_THRESHOLD_TESTNET = 8192;
	public static int DIFF_FILTER_THRESHOLD = 8192;

	public static int nDifficultySwitchHeight = 8192;
	public static int nDifficultySwitchHeightTwo = 62773;

	public static final int getInterval(int height, boolean testNet) {
		if (height < nDifficultySwitchHeight)
			return (int) nOriginalInterval; // 1080
		else if (height < nDifficultySwitchHeightTwo)
			return (int) nFilteredInterval; // 108
		else
			return INTERVAL_3;
	}

	public static final int getIntervalForCheckpoints(int height,
			boolean testNet) {
		if (height < 8050)
			return (int) nOriginalInterval; // 2016
		else if (height < nDifficultySwitchHeightTwo)
			return (int) nOriginalInterval; // 2016
		else
			return (int) nOriginalInterval / 4; // 504
	}

	public static final int getTargetTimespan(int height, boolean testNet) {
		if (height < nDifficultySwitchHeight)
			return TARGET_TIMESPAN_0; // 3.5 days
		else
			return TARGET_TIMESPAN; // 72 min
	}

	public static int getMaxTimeSpan(int value, int height, boolean testNet) {
		if (height < nDifficultySwitchHeight)
			return value * 4;
		else
			return value * 1; // not used
	}

	public static int getMinTimeSpan(int value, int height, boolean testNet) {
		if (height < nDifficultySwitchHeight)
			return value / 4;
		else
			return value * 1; // not used
	}

	public static int spendableCoinbaseDepth = 325; // main.h: static const int
													// COINBASE_MATURITY
	public static final double MAX_MONEY = 2718281828L; // main.h: MAX_MONEY
	public static final String MAX_MONEY_STRING = "2718281828"; // main.h:
																// MAX_MONEY

	public static final BigInteger DEFAULT_MIN_TX_FEE = BigInteger
			.valueOf(10000); // MIN_TX_FEE
	public static final BigInteger DUST_LIMIT = Utils.CENT; // main.h
															// CTransaction::GetMinFee
															// 0.01 coins

	public static final int PROTOCOL_VERSION = 70002; // version.h
														// PROTOCOL_VERSION
	public static final int MIN_PROTOCOL_VERSION = 70002; // version.h
															// MIN_PROTO_VERSION

	public static final boolean supportsBloomFiltering = true; // Requires
																// PROTOCOL_VERSION
																// 70000 in the
																// client

	// public static final int Port = 32581;// 32581; //protocol.h
	// GetDefaultPort(testnet=false)
	public static final int TestPort = 18523; // protocol.h
												// GetDefaultPort(testnet=true)

	//
	// Production
	//
	// public static final int AddressHeader = 65; // base58.h
	// CBitcoinAddress::PUBKEY_ADDRESS
	public static final int p2shHeader = 5; // base58.h
											// CBitcoinAddress::SCRIPT_ADDRESS

	public static final int dumpedPrivateKeyHeader = 128; // common to all coins

	// Genesis Block Information from main.cpp: LoadBlockIndex
	static public long genesisBlockDifficultyTarget = (0x1e0ffff0L); // main.cpp:
																		// LoadBlockIndex

	final static public long PacketMagic;
	final static public String genesisHash; // main.cpp: hashGenesisBlock
	final static public long genesisBlockTime;
	final static public long genesisBlockNonce;
	final static public int Port;
	final static public int AddressHeader; // base58.h
	static {// tcgen
		boolean isTestNet = true;
		if (isTestNet) {
			// testnet
			//
			Port = 18523;

			AddressHeader = 66; // base58.h

			PacketMagic = 0xfcc1b7dc; // 0xfb, 0xc0, 0xb6, 0xdb

			genesisHash = "b44cc80bfa2a5629c618d5a3c07f2400462d781c1a289379576dbb4fc29618c5"; // main.cpp:
																								// hashGenesisBlock
			genesisBlockTime = 1394365990L; // main.cpp: LoadBlockIndex static
											// public long
			genesisBlockNonce = (25210602); // main.cpp: LoadBlockIndex

		} else {
			// main

			Port = 32581;// 32581; //protocol.h

			AddressHeader = 65; // base58.h

			PacketMagic = 0xc1c1c1c1; // 0xfb, 0xc0, 0xb6, 0xdb
			genesisHash = "c741436d354a9fb337fbd79e4b2750732571f00e8b78d04493f9e9283cf4ad0b"; // main.cpp:hashGenesisBlock
			genesisBlockTime = 1395705600L; // main.cpp:
											// LoadBlockIndex
			genesisBlockNonce = (24706485); // main.cpp:

		}
	}

	static public int genesisBlockValue = 0; // main.cpp: LoadBlockIndex
	// taken from the raw data of the block explorer
	static public String genesisXInBytes = "04ffff001d01043954656d706c6520636f696e2077617320626f726e206f6e204d61726368203235746820323031342062792054656d706c65436f696e2e636f6d"; // "Boston Herald - 21/May/2013 - IRS Official to Take Fifth to Avoid Testifying"
	static public String genessiXOutBytes = "04798c6dad69b406e457605433f36f5f3652e7b0f62132f584bee3ae08b85332ea3183d9633a745a6ee829b03725885c8241c90b2846a9d3674b3051db502e7ee5";

	// net.cpp strDNSSeed
	static public String[] dnsSeeds = new String[] { "dnsseed.templecoin.com"

	};

	//
	// TestNet - digitalcoin - not tested
	//
	public static final boolean supportsTestNet = false;
	public static final int testnetAddressHeader = 65; // base58.h
														// CBitcoinAddress::PUBKEY_ADDRESS_TEST
	public static final int testnetp2shHeader = 196; // base58.h
														// CBitcoinAddress::SCRIPT_ADDRESS_TEST
	public static final long testnetPacketMagic = 0xfdf0f4fe; // 0xfc, 0xc1,
																// 0xb7, 0xdc
	public static final String testnetGenesisHash = "5e039e1ca1dbf128973bf6cff98169e40a1b194c3b91463ab74956f413b2f9c8";
	static public long testnetGenesisBlockDifficultyTarget = (0x1e0ffff0L); // main.cpp:
																			// LoadBlockIndex
	static public long testnetGenesisBlockTime = 1369198853L; // main.cpp:
																// LoadBlockIndex
	static public long testnetGenesisBlockNonce = (386245382); // main.cpp:
																// LoadBlockIndex

	// main.cpp GetBlockValue(height, fee)
	public static BigInteger GetBlockReward(long diffTime, long nBits,
			int nHeight, double nFees) {
		if (nHeight == 0)
			return Utils.toNanoCoins(0, 0);

		if (nHeight == 1)
			return Utils.toNanoCoins(2713250000L, 0);

		BigInteger nSubsidy = Utils.toNanoCoins(1, 0).shiftRight(
				nHeight / subsidyDecreaseBlockCount);

		nSubsidy.add(Utils.toNanoCoins(0, (int) (nFees * 100000000)));
		return nSubsidy;
	}

	public static int subsidyDecreaseBlockCount = 325000; // main.cpp
															// GetBlockValue(height,
															// fee)

	public static BigInteger proofOfWorkLimit = Utils

	.decodeCompactBits(0x1e0fffffL); // main.cpp bnProofOfWorkLimit
										// (~uint256(0) >> 20); //
										// digitalcoin: starting
										// difficulty is 1 / 2^12

	static public String[] testnetDnsSeeds = new String[] { "not supported" };
	// from main.h: CAlert::CheckSignature
	public static final String SATOSHI_KEY = "04798c6dad69b406e457605433f36f5f3652e7b0f62132f584bee3ae08b85332ea3183d9633a745a6ee829b03725885c8241c90b2846a9d3674b3051db502e7ee5";
	public static final String TESTNET_SATOSHI_KEY = "04826AC11FCF383A1E0F21E2A76807D082FF4E7F139111A7768E4F5A35A5653A2D44A8E19BC8B55AEDC9F9238D424BDC5EBD6D2BAF9CB3D30CEDEA35C47C8350A0";

	/**
	 * The string returned by getId() for the main, production network where
	 * people trade things.
	 */
	public static final String ID_MAINNET = "org.templecoin.production";
	/** The string returned by getId() for the testnet. */
	public static final String ID_TESTNET = "org.templecoin.test";
	/** Unit test network. */
	public static final String ID_UNITTESTNET = "com.google.templecoin.unittest";

	// checkpoints.cpp Checkpoints::mapCheckpoints
	public static void initCheckpoints(Map<Integer, Sha256Hash> checkpoints) {

		// checkpoints.put( 0, new
		// Sha256Hash("c741436d354a9fb337fbd79e4b2750732571f00e8b78d04493f9e9283cf4ad0b"));
		/*
		 * checkpoints.put( 0, new Sha256Hash(
		 * "b44cc80bfa2a5629c618d5a3c07f2400462d781c1a289379576dbb4fc29618c5"));
		 * checkpoints.put( 1, new Sha256Hash(
		 * "3c29579880deac63f92d20ce523d754f3a24ed3ac3a784822a5d72419d3e1a73"));
		 * checkpoints.put( 500, new Sha256Hash(
		 * "a94eeb08e4bae457e8808ec2aeab8d507aa8fc2be50aa0abe057e78bfe7d562d"));
		 */

		checkpoints
				.put(0,
						new Sha256Hash(
								"c741436d354a9fb337fbd79e4b2750732571f00e8b78d04493f9e9283cf4ad0b"));
		checkpoints
				.put(2016,
						new Sha256Hash(
								"26fc7503f3628d3ef64a1eb21379507cbe9f87c69076aa44d5106bd058f83f10"));

	}
}
