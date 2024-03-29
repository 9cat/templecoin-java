/*
 * Copyright 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.bitcoin.examples;

import com.google.bitcoin.core.*;
import com.google.bitcoin.core.Wallet.SendRequest;
import com.google.bitcoin.params.MainNetParams;
import com.google.bitcoin.params.TestNet3Params;
import com.google.bitcoin.store.BlockStore;
import com.google.bitcoin.store.H2FullPrunedBlockStore;
import com.google.bitcoin.store.MemoryBlockStore;

import java.io.File;
import java.math.BigInteger;
import java.net.InetAddress;

/**
 * RefreshWallet loads a wallet, then processes the block chain to update the
 * transaction pools within it.
 */
public class RefreshWallet {
	public static void main(String[] args) throws Exception {
		// File file = new File(args[0]);
		// File file = new File("t:/test.wallet.dat");
		// Wallet wallet = Wallet.loadFromFile(file);

		final NetworkParameters params = MainNetParams.get();
		

		//Wallet wallet = new Wallet(params);
		 File file = new File("t:/temple/TPC/templecoinj.wallet.dat");
		Wallet wallet = Wallet.loadFromFile(file);

		System.out.println(wallet.toString());

		// Set up the components and link them together.
//		final NetworkParameters params = TestNet3Params.get();
		
		//BlockStore blockStore = new MemoryBlockStore(params);
		BlockStore blockStore = new H2FullPrunedBlockStore(params,"test",1);
		
		BlockChain chain = new BlockChain(params, wallet, blockStore);

		
		
		final PeerGroup peerGroup = new PeerGroup(params, chain);
//		peerGroup.addAddress(new PeerAddress(InetAddress.getLocalHost()));
		PeerAddress addr;
		addr= new PeerAddress(InetAddress.getByName("mc.9cat.net"), params.getPort());
		peerGroup.addAddress(addr);
		addr= new PeerAddress(InetAddress.getByName("192.241.232.209"), params.getPort());
		peerGroup.addAddress(addr);
		addr= new PeerAddress(InetAddress.getByName("192.241.215.76"), params.getPort());
		peerGroup.addAddress(addr);

		
		peerGroup.start();
		

		wallet.addEventListener(new AbstractWalletEventListener() {
			@Override
			public synchronized void onCoinsReceived(Wallet w, Transaction tx,
					BigInteger prevBalance, BigInteger newBalance) {
				System.out.println("\nReceived tx " + tx.getHashAsString());
				System.out.println(tx.toString());
			}
		});

		// Now download and process the block chain.
		peerGroup.downloadBlockChain();
		peerGroup.stop();
		//wallet.saveToFile(file);
		
		
	
		//wallet.sendCoins(SendRequest.to(destination, value).);
		System.out.println("\nDone!\n");
		System.out.println(wallet.toString());
		wallet.saveToFile(file);
	}
}
