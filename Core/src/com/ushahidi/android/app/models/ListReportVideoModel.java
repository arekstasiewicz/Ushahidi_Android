/**
 ** Copyright (c) 2010 Ushahidi Inc
 ** All rights reserved
 ** Contact: team@ushahidi.com
 ** Website: http://www.ushahidi.com
 **
 ** GNU Lesser General Public License Usage
 ** This file may be used under the terms of the GNU Lesser
 ** General Public License version 3 as published by the Free Software
 ** Foundation and appearing in the file LICENSE.LGPL included in the
 ** packaging of this file. Please review the following information to
 ** ensure the GNU Lesser General Public License version 3 requirements
 ** will be met: http://www.gnu.org/licenses/lgpl.html.
 **
 **
 ** If you have questions regarding the use of this file, please contact
 ** Ushahidi developers at team@ushahidi.com.
 **
 **/

package com.ushahidi.android.app.models;

import java.util.ArrayList;
import java.util.List;

import com.ushahidi.android.app.database.Database;
import com.ushahidi.android.app.entities.MediaEntity;

/**
 * @author eyedol
 */
public class ListReportVideoModel extends Model {

	private int id;

	private String video;

	private List<MediaEntity> mMedia;

	private List<ListReportVideoModel> mVideoModel;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVideo() {
		return this.video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public boolean load(int reportId) {
		mMedia = Database.mMediaDao.fetchReportVideo(reportId);
		if (mMedia != null) {
			return true;
		}
		return false;
	}

	public List<ListReportVideoModel> getVideos() {
		mVideoModel = new ArrayList<ListReportVideoModel>();

		if (mMedia != null && mMedia.size() > 0) {
			ListReportVideoModel videoModel = new ListReportVideoModel();
			videoModel.setId(mMedia.get(0).getDbId());
			videoModel.setVideo(mMedia.get(0).getLink());
			mVideoModel.add(videoModel);
		}

		return mVideoModel;
	}

	public List<ListReportVideoModel> getVideosByReportId(int reportId) {
		mVideoModel = new ArrayList<ListReportVideoModel>();
		mMedia = Database.mMediaDao.fetchReportVideo(reportId);
		if (mMedia != null && mMedia.size() > 0) {
			for (MediaEntity item : mMedia) {
				ListReportVideoModel videoModel = new ListReportVideoModel();
				videoModel.setId(item.getDbId());
				videoModel.setVideo(item.getLink());
				mVideoModel.add(videoModel);
			}
		}

		return mVideoModel;
	}

	public int totalReportVideos() {
		if (mMedia != null && mMedia.size() > 0) {
			return mMedia.size();
		}
		return 0;
	}

}
