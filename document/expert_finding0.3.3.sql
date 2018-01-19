/*
Navicat MySQL Data Transfer

Source Server         : link
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : expert_finding

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-01-19 21:39:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `expert`
-- ----------------------------
DROP TABLE IF EXISTS `expert`;
CREATE TABLE `expert` (
  `expert_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `expert_name` varchar(255) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL COMMENT '1男2女',
  `contact` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `education` varchar(255) DEFAULT NULL,
  `nation` varchar(255) DEFAULT NULL,
  `political_status` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `expert_score` double DEFAULT NULL,
  `ico_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`expert_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of expert
-- ----------------------------
INSERT INTO `expert` VALUES ('1', '俞士纶', '1', '(312) 996-0498', 'psyu@cs.uic.edu', '博士', '汉', '群众', '1969-10-01', '7', '1_ico.ico');
INSERT INTO `expert` VALUES ('2', '韩家炜', '1', '(217) 333-6903', 'hanj@Illinois.edu', '博士', '汉', '党员', '1964-11-20', '8', null);
INSERT INTO `expert` VALUES ('3', '李建中', '1', '86415827', 'lijzn@hit.edu.cn', '博士', '汉', '党员', '1974-07-11', '5', null);
INSERT INTO `expert` VALUES ('4', '石川', '1', '010-62283779', 'shichuan@bupt.du.cn', '博士', '汉', '党员', '1978-07-21', '6', null);
INSERT INTO `expert` VALUES ('5', 'Chi Wang ', '1', '87698909', 'ChiWang@microsoft.com ', '博士', '汉', '群众', '1983-11-14', '7', null);

-- ----------------------------
-- Table structure for `exp_exp`
-- ----------------------------
DROP TABLE IF EXISTS `exp_exp`;
CREATE TABLE `exp_exp` (
  `exp_exp_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `expert1_id` int(10) unsigned NOT NULL,
  `expert2_id` int(10) unsigned NOT NULL,
  `exp_relate` double DEFAULT NULL,
  PRIMARY KEY (`exp_exp_id`),
  KEY `expert1_id` (`expert1_id`),
  KEY `expert2_id` (`expert2_id`),
  CONSTRAINT `exp_exp_ibfk_1` FOREIGN KEY (`expert1_id`) REFERENCES `expert` (`expert_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `exp_exp_ibfk_2` FOREIGN KEY (`expert2_id`) REFERENCES `expert` (`expert_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exp_exp
-- ----------------------------
INSERT INTO `exp_exp` VALUES ('1', '1', '3', '4.5');
INSERT INTO `exp_exp` VALUES ('2', '1', '2', '6.4');
INSERT INTO `exp_exp` VALUES ('3', '2', '3', '8.7');
INSERT INTO `exp_exp` VALUES ('4', '3', '4', '6.3');
INSERT INTO `exp_exp` VALUES ('5', '4', '2', '4.5');
INSERT INTO `exp_exp` VALUES ('6', '5', '3', '8.3');
INSERT INTO `exp_exp` VALUES ('7', '4', '1', '3.9');
INSERT INTO `exp_exp` VALUES ('8', '5', '2', '7.4');

-- ----------------------------
-- Table structure for `exp_loc`
-- ----------------------------
DROP TABLE IF EXISTS `exp_loc`;
CREATE TABLE `exp_loc` (
  `exp_loc_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `expert_id` int(10) unsigned NOT NULL,
  `location_id` int(10) unsigned NOT NULL,
  `exp_loc_start_time` datetime DEFAULT NULL,
  `exp_loc_end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`exp_loc_id`),
  KEY `expert_id` (`expert_id`),
  KEY `location_id` (`location_id`),
  CONSTRAINT `exp_loc_ibfk_1` FOREIGN KEY (`expert_id`) REFERENCES `expert` (`expert_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `exp_loc_ibfk_2` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exp_loc
-- ----------------------------
INSERT INTO `exp_loc` VALUES ('1', '1', '5', '2008-11-14 19:24:24', '2010-06-16 16:16:59');
INSERT INTO `exp_loc` VALUES ('2', '2', '3', '2010-07-15 19:24:54', '2015-11-29 16:17:11');
INSERT INTO `exp_loc` VALUES ('3', '3', '2', '2004-02-14 19:25:15', '2015-11-23 16:17:23');
INSERT INTO `exp_loc` VALUES ('4', '4', '1', '2014-07-07 19:25:37', '2017-11-29 16:17:36');
INSERT INTO `exp_loc` VALUES ('5', '5', '4', '2009-03-12 19:25:54', null);

-- ----------------------------
-- Table structure for `exp_org`
-- ----------------------------
DROP TABLE IF EXISTS `exp_org`;
CREATE TABLE `exp_org` (
  `exp_org_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `expert_id` int(10) unsigned NOT NULL,
  `org_id` int(10) unsigned NOT NULL,
  `exp_org_start_time` datetime DEFAULT NULL,
  `exp_org_end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`exp_org_id`),
  KEY `expert_id` (`expert_id`),
  KEY `org_id` (`org_id`),
  CONSTRAINT `exp_org_ibfk_1` FOREIGN KEY (`expert_id`) REFERENCES `expert` (`expert_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `exp_org_ibfk_2` FOREIGN KEY (`org_id`) REFERENCES `org` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exp_org
-- ----------------------------
INSERT INTO `exp_org` VALUES ('1', '1', '1', '2008-11-14 19:19:39', '2014-06-29 16:33:33');
INSERT INTO `exp_org` VALUES ('2', '2', '2', '2010-06-14 19:20:15', '2015-11-29 16:33:40');
INSERT INTO `exp_org` VALUES ('3', '3', '1', '2009-07-24 19:20:47', '2017-11-29 16:33:47');
INSERT INTO `exp_org` VALUES ('4', '4', '3', '2004-12-30 19:21:09', null);
INSERT INTO `exp_org` VALUES ('5', '5', '4', '2009-03-12 19:21:34', '2017-11-29 16:34:02');

-- ----------------------------
-- Table structure for `exp_res`
-- ----------------------------
DROP TABLE IF EXISTS `exp_res`;
CREATE TABLE `exp_res` (
  `exp_res_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `expert_id` int(10) unsigned NOT NULL,
  `result_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`exp_res_id`),
  KEY `expert_id` (`expert_id`),
  KEY `result_id` (`result_id`),
  CONSTRAINT `exp_res_ibfk_1` FOREIGN KEY (`expert_id`) REFERENCES `expert` (`expert_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `exp_res_ibfk_2` FOREIGN KEY (`result_id`) REFERENCES `result` (`result_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exp_res
-- ----------------------------
INSERT INTO `exp_res` VALUES ('1', '1', '1');
INSERT INTO `exp_res` VALUES ('2', '2', '1');
INSERT INTO `exp_res` VALUES ('3', '1', '2');
INSERT INTO `exp_res` VALUES ('4', '2', '3');
INSERT INTO `exp_res` VALUES ('5', '3', '4');
INSERT INTO `exp_res` VALUES ('6', '4', '5');
INSERT INTO `exp_res` VALUES ('7', '5', '6');
INSERT INTO `exp_res` VALUES ('8', '4', '7');
INSERT INTO `exp_res` VALUES ('9', '3', '8');
INSERT INTO `exp_res` VALUES ('10', '2', '9');
INSERT INTO `exp_res` VALUES ('11', '5', '10');
INSERT INTO `exp_res` VALUES ('12', '4', '8');

-- ----------------------------
-- Table structure for `field`
-- ----------------------------
DROP TABLE IF EXISTS `field`;
CREATE TABLE `field` (
  `fieid_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `field_name` varchar(255) DEFAULT NULL,
  `field_desc` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`fieid_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of field
-- ----------------------------
INSERT INTO `field` VALUES ('1', '数据挖掘', '数据挖掘（英语：Data mining），又译为资料探勘、数据采矿。它是数据库知识发现（英语：Knowledge-Discovery in Databases，简称：KDD)中的一个步骤。数据挖掘一般是指从大量的数据中通过算法搜索隐藏于其中信息的过程。数据挖掘通常与计算机科学有关，并通过统计、在线分析处理、情报检索、机器学习、专家系统（依靠过去的经验法则）和模式识别等诸多方法来实现上述目标。');
INSERT INTO `field` VALUES ('2', '数据库系统', '数据库系统（Database System），是由数据库及其管理软件组成的系统。\r\n数据库系统是为适应数据处理的需要而发展起来的一种较为理想的数据处理系统，也是一个为实际可运行的存储、维护和应用系统提供数据的软件系统，是存储介质 、处理对象和管理系统的集合体。');
INSERT INTO `field` VALUES ('3', '信息网络', '信息网络专指电子信息传输的通道,是构成这种通道的线路、设备的总称,是\"网络\"的一种。');
INSERT INTO `field` VALUES ('4', '社交网络', '社交网络即社交网络服务，源自英文SNS（Social Network Service）的翻译，中文直译为社交网络服务，意译为社交网络服务。\r\n社交网络含义包括硬件、软件、服务及应用，由于四字构成的词组更符合中国人的构词习惯，因此人们习惯上用社交网络来代指SNS（Social Network Service）。');
INSERT INTO `field` VALUES ('5', '分布式系统', '分布式系统（distributed system）是建立在网络之上的软件系统。正是因为软件的特性，所以分布式系统具有高度的内聚性和透明性。因此，网络和分布式系统之间的区别更多的在于高层软件（特别是操作系统），而不是硬件。内聚性是指每一个数据库分布节点高度自治，有本地的数据库管理系统。透明性是指每一个数据库分布节点对用户的应用来说都是透明的，看不出是本地还是远程。在分布式数据库系统中，用户感觉不到数据是分布的，即用户不须知道关系是否分割、有无副本、数据存于哪个站点以及事务在哪个站点上执行等。');
INSERT INTO `field` VALUES ('6', '文本挖掘', '《文本挖掘》是 2009年08月由人民邮电出版社出版的图书，作者是学者费尔德曼。该书中涵盖了核心文本挖掘操作、文本挖掘预处理技术、分类、聚类、信息提取、信息提取的概率模型、预处理应用、可视化方法、链接分析、文本挖掘应用等内容，很好地结合了文本挖掘的理论和实践。');
INSERT INTO `field` VALUES ('7', '机器学习', '机器学习(Machine Learning, ML)是一门多领域交叉学科，涉及概率论、统计学、逼近论、凸分析、算法复杂度理论等多门学科。专门研究计算机怎样模拟或实现人类的学习行为，以获取新的知识或技能，重新组织已有的知识结构使之不断改善自身的性能。\r\n它是人工智能的核心，是使计算机具有智能的根本途径，其应用遍及人工智能的各个领域，它主要使用归纳、综合而不是演绎。');
INSERT INTO `field` VALUES ('8', '网络安全', '网络安全是指网络系统的硬件、软件及其系统中的数据受到保护，不因偶然的或者恶意的原因而遭受到破坏、更改、泄露，系统连续可靠正常地运行，网络服务不中断。');
INSERT INTO `field` VALUES ('9', '图像处理', '图像处理(image processing)，用计算机对图像进行分析，以达到所需结果的技术。又称影像处理。图像处理一般指数字图像处理。数字图像是指用工业相机、摄像机、扫描仪等设备经过拍摄得到的一个大的二维数组，该数组的元素称为像素，其值称为灰度值。图像处理技术一般包括图像压缩，增强和复原，匹配、描述和识别3个部分。');
INSERT INTO `field` VALUES ('10', '强化学习', '强化学习(reinforcement learning)，又称再励学习、评价学习，是一种重要的机器学习方法，在智能控制机器人及分析预测等领域有许多应用。\r\n但在传统的机器学习分类中没有提到过强化学习，而在连接主义学习中，把学习算法分为三种类型，即非监督学习(unsupervised learning)、监督学习(supervised leaning)和强化学习。');

-- ----------------------------
-- Table structure for `importance`
-- ----------------------------
DROP TABLE IF EXISTS `importance`;
CREATE TABLE `importance` (
  `importance_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `result_id` bigint(20) unsigned NOT NULL,
  `importance_score` double DEFAULT NULL,
  PRIMARY KEY (`importance_id`),
  KEY `result_id` (`result_id`),
  CONSTRAINT `importance_ibfk_1` FOREIGN KEY (`result_id`) REFERENCES `result` (`result_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of importance
-- ----------------------------
INSERT INTO `importance` VALUES ('1', '1', '6.7');
INSERT INTO `importance` VALUES ('2', '2', '8.3');
INSERT INTO `importance` VALUES ('3', '3', '4.5');
INSERT INTO `importance` VALUES ('4', '4', '5.4');
INSERT INTO `importance` VALUES ('5', '5', '4.6');
INSERT INTO `importance` VALUES ('6', '6', '7.8');
INSERT INTO `importance` VALUES ('7', '7', '7.9');
INSERT INTO `importance` VALUES ('8', '8', '8.5');
INSERT INTO `importance` VALUES ('9', '9', '7.6');
INSERT INTO `importance` VALUES ('10', '10', '8.2');
INSERT INTO `importance` VALUES ('11', '11', '7.9');

-- ----------------------------
-- Table structure for `location`
-- ----------------------------
DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
  `location_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `country` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of location
-- ----------------------------
INSERT INTO `location` VALUES ('1', '中国', null, '北京');
INSERT INTO `location` VALUES ('2', '中国', null, '上海');
INSERT INTO `location` VALUES ('3', '中国', '江苏', '南京');
INSERT INTO `location` VALUES ('4', '美国', '伊利诺伊州', '芝加哥');
INSERT INTO `location` VALUES ('5', '美国', '马萨诸塞州', '波士顿');

-- ----------------------------
-- Table structure for `org`
-- ----------------------------
DROP TABLE IF EXISTS `org`;
CREATE TABLE `org` (
  `org_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `org_name` varchar(255) DEFAULT NULL,
  `org_score` double DEFAULT NULL,
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of org
-- ----------------------------
INSERT INTO `org` VALUES ('1', '伊利诺伊大学', '7.5');
INSERT INTO `org` VALUES ('2', '清华大学', '7.3');
INSERT INTO `org` VALUES ('3', '北京邮电大学', '5.3');
INSERT INTO `org` VALUES ('4', '上海交通大学', '5.7');

-- ----------------------------
-- Table structure for `persistent_logins`
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------

-- ----------------------------
-- Table structure for `publication`
-- ----------------------------
DROP TABLE IF EXISTS `publication`;
CREATE TABLE `publication` (
  `publication_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `publication_name` varchar(255) DEFAULT NULL,
  `publication_score` double DEFAULT NULL,
  PRIMARY KEY (`publication_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of publication
-- ----------------------------
INSERT INTO `publication` VALUES ('1', 'IEEE Trans. Knowl. Data Eng', '8.7');
INSERT INTO `publication` VALUES ('2', 'ACM TIST', '7.9');
INSERT INTO `publication` VALUES ('3', 'MDM', '4.2');
INSERT INTO `publication` VALUES ('4', 'TOSN', '5.7');
INSERT INTO `publication` VALUES ('5', 'KDD', '8.7');
INSERT INTO `publication` VALUES ('6', 'ACM Knowledge Discovery and Data Mining', '5.4');
INSERT INTO `publication` VALUES ('7', 'SDM', '6.7');

-- ----------------------------
-- Table structure for `result`
-- ----------------------------
DROP TABLE IF EXISTS `result`;
CREATE TABLE `result` (
  `result_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `result_name` varchar(255) DEFAULT NULL,
  `result_desc` varchar(1000) DEFAULT NULL,
  `result_type` int(11) DEFAULT NULL COMMENT '1论文/2专利/3获利',
  `result_time` datetime DEFAULT NULL,
  `cited_num` int(11) DEFAULT NULL,
  `award` int(11) DEFAULT NULL COMMENT '0国际级/1国家级/2省级',
  `score` varchar(255) DEFAULT NULL,
  `pubilcation_id` int(10) unsigned NOT NULL,
  `author_ranking` int(11) DEFAULT NULL,
  PRIMARY KEY (`result_id`),
  KEY `pubilcation_id` (`pubilcation_id`),
  CONSTRAINT `result_ibfk_1` FOREIGN KEY (`pubilcation_id`) REFERENCES `publication` (`publication_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of result
-- ----------------------------
INSERT INTO `result` VALUES ('1', 'Mining Reliable Information from Passively and Actively Crowdsourced Data.', 'Recent years have witnessed an astonishing growth of crowd-contributed data, which has become a powerful information source that covers almost every aspect of our lives. This big treasure trove of information has fundamentally changed the ways in which we learn about our world. Crowdsourcing has attracted considerable attentions with various approaches developed to utilize these enormous crowdsourced data from different perspectives. From the data collection perspective, crowdsourced data can be divided into two types: \"passively\" crowdsourced data and \"actively\" crowdsourced data; from task perspective, crowdsourcing research includes information aggregation, budget allocation, worker incentive mechanism, etc. To answer the need of a systematic introduction of the field and comparison of the techniques, we will present an organized picture on crowdsourcing methods in this tutorial. The covered topics will be interested for both advanced researchers and beginners in this field.', '1', '2016-07-28 19:38:18', '3', '0', '一等', '3', '1');
INSERT INTO `result` VALUES ('2', 'CoType: Joint Extraction of Typed Entities and Relations with Knowledge Bases.', 'Abstract: Extracting entities and relations for types of interest from text is important for understanding massive text corpora. Traditionally, systems of entity relation extraction have relied on human-annotated corpora for training and adopted an incremental pipeline. Such systems require additional human expertise to be ported to a new domain, and are vulnerable to errors cascading down the pipeline. In this paper, we investigate joint extraction of typed entities and relations with labeled data heuristically obtained from knowledge bases (i.e., distant supervision).', '1', '2015-02-19 19:39:25', '4', '1', '二等', '1', '2');
INSERT INTO `result` VALUES ('3', 'A probabilistic approach to detect mixed periodic patterns from moving object data.', 'The prevalence of moving object data (MOD) brings new opportunities for behavior related research. Periodic behavior is one of the most important behaviors of moving objects. However, the existing methods of detecting periodicities assume a moving object either does not have any periodic behavior at all or just has a single periodic behavior in one place. Thus they are incapable of dealing with many real world situations whereby a moving object may have multiple periodic behaviors mixed together. Aiming at addressing this problem, this paper proposes a probabilistic periodicity detection method called MPDA. MPDA first identifies high dense regions by the kernel density method, then generates revisit time sequences based on the dense regions, and at last adopts a filter-refine paradigm to detect mixed periodicities. ', '2', '2017-10-10 19:39:57', '1', '2', '三等', '4', '3');
INSERT INTO `result` VALUES ('4', 'Estimating Urban Traffic Congestions with Multi-sourced Data.', 'The prevalence of moving object data (MOD) brings new opportunities for behavior related research. Periodic behavior is one of the most important behaviors of moving objects. However, the existing methods of detecting periodicities assume a moving object either does not have any periodic behavior at all or just has a single periodic behavior in one place. Thus they are incapable of dealing with many real world situations whereby a moving object may have multiple periodic behaviors mixed together. Aiming at addressing this problem, this paper proposes a probabilistic periodicity detection method called MPDA. ', '1', '2015-07-14 19:41:26', '5', '1', '一等', '6', '4');
INSERT INTO `result` VALUES ('5', 'Multi-source Hierarchical Prediction Consolidation', 'In big data applications such as healthcare data mining, due to privacy concerns, it is necessary to collect predictions from multiple information sources for the same instance, with raw features being discarded or withheld when aggregating multiple predictions. Besides, crowd-sourced labels need to be aggregated to estimate the ground truth of the data. Due to the imperfection caused by predictive models or human crowdsourcing workers, noisy and conflicting information is ubiquitous and inevitable. Although state-of-the-art aggregation methods have been proposed to handle label spaces with flat structures, as the label space is becoming more and more complicated, aggregation under a label hierarchical structure becomes necessary but has been largely ignored. ', '2', '2017-11-08 19:41:47', '1', '2', '二等', '5', '1');
INSERT INTO `result` VALUES ('6', 'Spatio-Temporal Tensor Analysis for Whole-Brain fMRI Classification.', 'Abstract Owing to prominence as a research and diagnostic tool in human brain mapping, whole-brain fMRI image analysis has been the focus of intense investigation. Conventionally, input fMRI brain images are converted into vectors or matrices and adapted in kernel based classifiers. fMRI data, however, are inherently coupled with sophisticated spatio-temporal tensor structure (i.e., 3D space 脳 time). Valuable structural information will be lost if the tensors are converted into vectors. Furthermore, time series fMRI data are noisy, involving time shif', '3', '2017-06-07 19:42:26', '0', '0', '一等', '2', '2');
INSERT INTO `result` VALUES ('7', 'Multi-graph Clustering Based on Interior-Node Topology with Applications to Brain Networks.', 'Abstract Learning from graph data has been attracting much attention recently due to its importance in many scientific applications, where objects are represented as graphs. In this paper, we study the problem of multi-graph clustering (i.e., clustering multiple graphs). We propose a multi-graph clustering approach (MGCT) based on the interior-node topology of graphs. Specifically, we extract the interior-node topological structure of each graph through a sparsity-inducing interior-node clustering. We merge the interior-node clustering stage and the multi-graph clustering stage into a unified iterative framework, where the multi-graph clustering will influence the interior-node clustering and the updated interior-node clustering results will be further exerted on multi-graph clustering. We apply MGCT on two real brain network data sets (i.e., ADHD and HIV). Experimental results demonstrate the superior performance of the proposed model on multi-graph clustering.', '1', '2014-07-14 19:42:52', '9', '2', '二等', '7', '3');
INSERT INTO `result` VALUES ('8', 'Trust Hole Identification in Signed Networks.', 'Abstract In the trust-centric context of signed networks, the social links among users are associated with specific polarities to denote the attitudes (trust vs distrust) among the users. Different from traditional unsigned social networks, the diffusion of information in signed networks can be affected by the link polarities and users’ positions significantly. In this paper, a new concept called “trust hole” is introduced to characterize the advantages of specific users’ positions in signed networks. To uncover the trust holes, a novel trust hole detection framework named “Social Community based tRust hOLe expLoration” (Scroll) is proposed in this paper. Framework Scroll is based on the signed community detection technique. By removing the potential trust hole candidates, Scroll aims at maximizing the community detection cost drop to identify the optimal set of trust holes. Extensive experiments have been done on real-world signed network datasets to show the effectiveness of Scroll.', '1', '2017-11-07 19:43:14', '5', '1', '三等', '2', '4');
INSERT INTO `result` VALUES ('9', 'Mining Brain Networks Using Multiple Side Views for Neurological Disorder Identification', 'Mining discriminative subgraph patterns from graph data has attracted great interest in recent years. It has a wide variety of applications in disease diagnosis, neuroimaging, etc. Most research on subgraph mining focuses on the graph representation alone. However, in many real-world applications, the side information is available along with the graph data. For example, for neurological disorder identification, in addition to the brain networks derived from neuroimaging data, hundreds of clinical, immunologic, serologic and cognitive measures may also be documented for each subject. These measures compose multiple side views encoding a tremendous amount of supplemental information for diagnostic purposes, yet are often ignored. In this paper, we study the problem of discriminative subgraph selection using multiple side views and propose a novel solution to find an optimal set of subgraph features for graph classification by exploring a plurality of side views. ', '1', '2016-06-14 19:46:20', '6', '0', '一等', '3', '1');
INSERT INTO `result` VALUES ('10', 'Reconstruction Privacy: Enabling Statistical Learning.', 'Non-independent reasoning (NIR) allows the information about one record in the data to be learnt from the information of other records in the data. Most posterior/prior based privacy criteria consider NIR as a privacy violation and require to smooth the distribution of published data to avoid sensitive NIR. The drawback of this approach is that it limits the utility of learning statistical relationships. The differential privacy criterion considers NIR as a non-privacy violation, therefore, enables learning statistical relationships, but at the cost of potential disclosures through NIR. ', '1', '2017-02-07 19:43:33', '7', '2', '二等', '6', '2');
INSERT INTO `result` VALUES ('11', 'Ensemble of Diverse Sparsifications for Link Prediction in Large-Scale Networks', 'Previous research has aimed to lower the cost of handling large networks by reducing the network size via sparsification. However, when many edges are removed from the network, the information that can be used for link prediction becomes rather limited, and the prediction accuracy thereby drops significantly. To address this issue, we propose a framework called Diverse Ensemble of Drastic Sparsification (DEDS), which constructs ensemble classifiers with good accuracy while keeping the prediction time short. DEDS includes various sparsification methods that are designed to preserve different measures of a network.', '1', '2017-11-28 19:43:54', '3', '2', '三等', '5', '3');

-- ----------------------------
-- Table structure for `res_field`
-- ----------------------------
DROP TABLE IF EXISTS `res_field`;
CREATE TABLE `res_field` (
  `res_field_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `result_id` bigint(20) unsigned NOT NULL,
  `field_id` int(10) unsigned NOT NULL,
  `res_field_relate` double DEFAULT NULL,
  PRIMARY KEY (`res_field_id`),
  KEY `result_id` (`result_id`),
  KEY `field_id` (`field_id`),
  CONSTRAINT `res_field_ibfk_1` FOREIGN KEY (`result_id`) REFERENCES `result` (`result_id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `res_field_ibfk_2` FOREIGN KEY (`field_id`) REFERENCES `field` (`fieid_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of res_field
-- ----------------------------
INSERT INTO `res_field` VALUES ('1', '1', '1', '6.4');
INSERT INTO `res_field` VALUES ('2', '1', '2', '3.4');
INSERT INTO `res_field` VALUES ('3', '2', '2', '6.7');
INSERT INTO `res_field` VALUES ('4', '3', '4', '7.4');
INSERT INTO `res_field` VALUES ('5', '3', '7', '3.5');
INSERT INTO `res_field` VALUES ('6', '4', '3', '4.5');
INSERT INTO `res_field` VALUES ('7', '5', '5', '4.9');
INSERT INTO `res_field` VALUES ('8', '6', '4', '5.6');
INSERT INTO `res_field` VALUES ('9', '7', '3', '7.9');
INSERT INTO `res_field` VALUES ('10', '8', '4', '8.4');
INSERT INTO `res_field` VALUES ('11', '9', '8', '9.1');
INSERT INTO `res_field` VALUES ('12', '10', '9', '7.4');
INSERT INTO `res_field` VALUES ('13', '11', '6', '6.7');
INSERT INTO `res_field` VALUES ('14', '11', '7', '8.3');
INSERT INTO `res_field` VALUES ('15', '10', '4', '3.2');
INSERT INTO `res_field` VALUES ('16', '9', '10', '2.5');
INSERT INTO `res_field` VALUES ('17', '4', '7', '8.4');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_SUPER');
INSERT INTO `role` VALUES ('2', 'ROLE_ADMIN');
INSERT INTO `role` VALUES ('3', 'ROLE_USER');

-- ----------------------------
-- Table structure for `trace`
-- ----------------------------
DROP TABLE IF EXISTS `trace`;
CREATE TABLE `trace` (
  `trace_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `expert_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`trace_id`),
  KEY `user_id` (`user_id`),
  KEY `expert_id` (`expert_id`),
  CONSTRAINT `trace_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `trace_ibfk_2` FOREIGN KEY (`expert_id`) REFERENCES `expert` (`expert_id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of trace
-- ----------------------------
INSERT INTO `trace` VALUES ('1', '1', '1');
INSERT INTO `trace` VALUES ('2', '1', '2');
INSERT INTO `trace` VALUES ('3', '2', '1');
INSERT INTO `trace` VALUES ('4', '2', '4');
INSERT INTO `trace` VALUES ('5', '4', '5');
INSERT INTO `trace` VALUES ('6', '4', '3');
INSERT INTO `trace` VALUES ('7', '5', '3');
INSERT INTO `trace` VALUES ('8', '2', '4');
INSERT INTO `trace` VALUES ('9', '5', '2');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `is_push` int(11) DEFAULT '0',
  `priv_level` int(11) NOT NULL COMMENT '2超级管理员1管理员0普通用户',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'ben', 'song@mail.com', '0', '3');
INSERT INTO `user` VALUES ('2', 'sherry', 'sherry@mail.com', '1', '3');
INSERT INTO `user` VALUES ('3', 'bill', '', '1', '3');
INSERT INTO `user` VALUES ('4', 'john', 'jhon@mailcom', '0', '3');
INSERT INTO `user` VALUES ('5', 'philip', 'philip@mail.com', '1', '3');
INSERT INTO `user` VALUES ('6', 'admin', null, '0', '2');
INSERT INTO `user` VALUES ('7', 'admin1', null, '1', '2');
INSERT INTO `user` VALUES ('8', 'admin2', null, '1', '2');
INSERT INTO `user` VALUES ('9', 'super', null, '0', '1');
INSERT INTO `user` VALUES ('34', 'test', null, '1', '3');
INSERT INTO `user` VALUES ('35', 'test1', null, '0', '3');

-- ----------------------------
-- Table structure for `user_pwd`
-- ----------------------------
DROP TABLE IF EXISTS `user_pwd`;
CREATE TABLE `user_pwd` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_pwd_ibfk_1` (`user_id`),
  CONSTRAINT `user_pwd_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_pwd
-- ----------------------------
INSERT INTO `user_pwd` VALUES ('1', '1', '$2a$10$kh8ORf6NoLQ4LClmnw.nbOjPs/Iu04/1PrNDlqIJM8i2/YFwEdU32');
INSERT INTO `user_pwd` VALUES ('2', '2', '$2a$10$B3SIovm1sazX2cnOlCPUFuivutkhYbhG5nE/GuVpLRAvpdPsDTKS6');
INSERT INTO `user_pwd` VALUES ('3', '3', '$2a$10$6LSRjR6wZNvWdAFZsY54BeOAkHkcWhScA/s0MKvCSxSh.WsbbHjfa');
INSERT INTO `user_pwd` VALUES ('4', '4', '$2a$10$ChMLWeJvBNlP5KiXBxQ.7eYkxq2dX19XXD5r86L5.jlcP/d3CiapS');
INSERT INTO `user_pwd` VALUES ('5', '5', '$2a$10$fb8ffJctvgwvQI2FQ9XJIeuYpCgHmmTtsrVslz9K1xw.8FCikMOnq');
INSERT INTO `user_pwd` VALUES ('6', '6', '$2a$10$vvHCktBiC73EM13UI.p8reApSaqn2I6ZeWaCuqkZfRiis.EwjFGIO');
INSERT INTO `user_pwd` VALUES ('7', '7', '$2a$10$72Hl6MhLZcPiIxej.A8rueWNPKQoiGQkPwXFzkWURr8qNo.l/u.7O');
INSERT INTO `user_pwd` VALUES ('8', '8', '$2a$10$vicft5mJHuxUqTbtnE7JDe2O/xjGgkedlGZIaemLRWPOb65gEemyS');
INSERT INTO `user_pwd` VALUES ('9', '9', '$2a$10$dUfuNOQfFe60Bun4GNQOLuqMwQReEcIT84aGrl7F8KDsHfDZFLTie');
INSERT INTO `user_pwd` VALUES ('31', '34', '$2a$10$J6g0Vy0IWEyjprnsbRkKb.uMfXRoJJQk8SogqqVdUSPK8twvVSQnC');
INSERT INTO `user_pwd` VALUES ('32', '35', '$2a$10$s8qWVnquRdOCuLQ42Pu.EOJhDZVFq/7WMVe2DZdBvHQD4Owaw0er6');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(10) unsigned NOT NULL,
  `rid` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_role_ibfk_1` (`rid`),
  KEY `user_role_ibfk_2` (`uid`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `role` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '6', '2');
INSERT INTO `user_role` VALUES ('2', '1', '3');
INSERT INTO `user_role` VALUES ('3', '9', '1');
INSERT INTO `user_role` VALUES ('4', '2', '3');
INSERT INTO `user_role` VALUES ('5', '3', '2');
INSERT INTO `user_role` VALUES ('6', '4', '3');
INSERT INTO `user_role` VALUES ('7', '5', '3');
INSERT INTO `user_role` VALUES ('8', '7', '2');
INSERT INTO `user_role` VALUES ('9', '8', '2');
INSERT INTO `user_role` VALUES ('33', '34', '3');
INSERT INTO `user_role` VALUES ('34', '35', '3');
